package com.chinaclear.sz.component.context;

import cn.hutool.core.bean.BeanUtil;
import com.chinaclear.sz.component.common.*;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean工厂
 */
public class DefaultBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry, SingletonBeanRegistry {
    /**
     * 单例池，一级缓存
     */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    /**
     * 早期单例池， 二级缓存
     */
    private Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();
    /**
     * 三级缓存， 存放需要执行AOP的bean
     */
    private Map<String, Object> singleFactories = new HashMap<>();

    public static final String CONFIGURATION_CLASS_POST_PROCESSOR_NAME = "ConfigurationClassPostProcessor";
    public static final String COMMON_ANNOTATION_BEAN_PROCESSOR_NAME = "CommonAnnotationPostProcessor";
    public static final String AUTOWIRED_ANNOTATION_BEAN_PROCESSOR_NAME = "AutowiredAnnotationPostProcessor";

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactory() {
        //注册解析@Configuration的配置类
        if (!containsBeanDefinition(CONFIGURATION_CLASS_POST_PROCESSOR_NAME)) {
            BeanDefinition beanDefinition = GenericBeanDefinition.buildBeanDefinition(ConfigurationClassPostProcessor.class, BeanType.SYSTEM_TYPE);
            registerBeanDefinition(beanDefinition.getBeanName(), beanDefinition);
        }

        //注册解析@Autowired的后置处理器
        if (!containsBeanDefinition(AUTOWIRED_ANNOTATION_BEAN_PROCESSOR_NAME)) {
            BeanDefinition beanDefinition = GenericBeanDefinition.buildBeanDefinition(AutowiredAnnotationPostProcessor.class, BeanType.SYSTEM_TYPE);
            registerBeanDefinition(beanDefinition.getBeanName(), beanDefinition);
        }

        //注册解析@Resource的后置处理器
        if (!containsBeanDefinition(COMMON_ANNOTATION_BEAN_PROCESSOR_NAME)) {
            BeanDefinition beanDefinition = GenericBeanDefinition.buildBeanDefinition(ConfigurationClassPostProcessor.class, BeanType.SYSTEM_TYPE);
            registerBeanDefinition(beanDefinition.getBeanName(), beanDefinition);
        }
    }

    @Override
    public <T> T getBeanByType(Class<T> classType) {
        return null;
    }

    @Override
    public Object getBeanByName(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public <T> T getBeanByName(String beanName, Class<T> classType) {
        if (!singletonObjects.containsKey(beanName)) {
            return null;
        }

        Object bean = singletonObjects.get(beanName);
        if (!classType.isAssignableFrom(bean.getClass())) {
            throw new RuntimeException("类型不匹配");
        }
        return (T) bean;
    }

    @Override
    public boolean containsBean(String name) {
        return singletonObjects.containsKey(name);
    }

    @Override
    public List<String> getBeanNamesByType(Class type) {
        if (Objects.isNull(type)) {
            return new ArrayList<>();
        }
        List<String> beanNames = new ArrayList<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            if (Objects.nonNull(beanDefinition) && type.isAssignableFrom(beanDefinition.getBeanClass())) {
                beanNames.add(beanName);
            }
        });
        return beanNames;
    }

    @Override
    public Object getBean(String beanName) {
        return getBean(beanName, null);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        if (!containsBean(beanName)) {
            beanDefinitionMap.put(beanName, beanDefinition);
        }
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        beanDefinitionMap.remove(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public void preInstantiateSingletons() {
        Set<String> beanNames = beanDefinitionMap.keySet();
        for (String beanName : beanNames) {
            getBean(beanName);
        }

        //触发Bean的最终回调
        for (String beanName : beanNames) {
            Object singleton = getSingleton(beanName);
            if (singleton instanceof SmartInitializingSingleton smartInitializingSingleton) {
                smartInitializingSingleton.afterSingleInstantiated();
            }
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return getSingleton(beanName, true);
    }

    @Override
    public Object getSingleton(String beanName, boolean allowEarlyReference) {
        return singletonObjects.get(beanName);
    }

    public <T> T getBean(String beanName, Class<T> requireType) {
        RootBeanDefinition rootBeanDefinition = mergeBeanDefinition(beanName, requireType);
        Object singleton = getSingleton(beanName);
        if (singleton == null) {
            //实例化前
            singleton = applyPostProcessBeforeInstantiation(beanName);

            if (singleton != null) {
                return adaptBeanInstance(beanName, singleton, requireType);
            }

            //实例化
            singleton = createBeanInstance(beanName, rootBeanDefinition);

            //实例化后
            applyPostProcessAfterInstantiation(beanName, singleton);

            //自动注入
            populateBean(beanName, singleton);

            //初始化前
            applyPostProcessBeforeInitialization(beanName, singleton);
            //初始化
            initializationBean(beanName, singleton);
            //初始化后
            applyPostProcessAfterInitialization(beanName, singleton);
        }

        return adaptBeanInstance(beanName, singleton, requireType);
    }

    private RootBeanDefinition mergeBeanDefinition(String beanName) {
        BeanDefinition definition = this.getBeanDefinition(beanName);
        RootBeanDefinition rootBeanDefinition = BeanUtil.copyProperties(definition, RootBeanDefinition.class);
        return rootBeanDefinition;
    }

    private void applyPostProcessAfterInitialization(String beanName, Object singleton) {
        List<String> beanNamesByType = getBeanNamesByType(BeanPostProcessor.class);
        for (String postProcessorBeanName : beanNamesByType) {
            InstantiationAwareBeanPostProcessor postProcessor = getBeanByName(postProcessorBeanName, InstantiationAwareBeanPostProcessor.class);
            if (postProcessor.isSupport(singleton)) {
                postProcessor.postProcessBeforeBeanInitialization(singleton);
            }
        }
    }

    private void initializationBean(String beanName, Object singleton) {

    }

    private void applyPostProcessBeforeInitialization(String beanName, Object singleton) {
        List<String> beanNamesByType = getBeanNamesByType(BeanPostProcessor.class);
        for (String postProcessorBeanName : beanNamesByType) {
            InstantiationAwareBeanPostProcessor postProcessor = getBeanByName(postProcessorBeanName, InstantiationAwareBeanPostProcessor.class);
            if (postProcessor.isSupport(singleton)) {
                postProcessor.postProcessBeforeBeanInitialization(singleton);
            }
        }
    }

    private void populateBean(String beanName, Object singleton) {
        List<String> beanNamesByType = getBeanNamesByType(InstantiationAwareBeanPostProcessor.class);
        for (String postProcessorBeanName : beanNamesByType) {
            InstantiationAwareBeanPostProcessor postProcessor = getBeanByName(postProcessorBeanName, InstantiationAwareBeanPostProcessor.class);
            if (postProcessor.isSupport(singleton)) {
                postProcessor.postProcessProperties(null, singleton, beanName);
            }
        }
    }

    private Object createBeanInstance(String beanName, RootBeanDefinition rootBeanDefinition) {
        //1. Spring可以通过Supplier获取Bean

        //2. 判断是否为@bean类型；
        if (rootBeanDefinition.getFactoryMethodName() != null) {
            rootBeanDefinition.getBeanMethod().setAccessible(true);
            //拿到Method方法所属的配置类的Bean
            Object configBean = getBeanByType(rootBeanDefinition.getConfigClass());
            try {
                Object object = rootBeanDefinition.getBeanMethod().invoke(configBean);
                return object;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        //3. Spring会进行推断构造方法，这里直接使用无参构造方法；
        if (rootBeanDefinition.getBeanClass() != null) {
            try {
                Constructor constructor = rootBeanDefinition.getBeanClass().getConstructor();
                Object newInstance = constructor.newInstance();
                return newInstance;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    private Object applyPostProcessAfterInstantiation(String beanName, Object singleton) {
        List<String> beanNamesByType = getBeanNamesByType(InstantiationAwareBeanPostProcessor.class);
        for (String postProcessorBeanName : beanNamesByType) {
            InstantiationAwareBeanPostProcessor postProcessor = getBeanByName(postProcessorBeanName, InstantiationAwareBeanPostProcessor.class);
            if (postProcessor.isSupport(singleton)) {
                postProcessor.postProcessAfterInstantiation(singleton, beanName);
            }
        }
        return singleton;
    }

    private Object applyPostProcessBeforeInstantiation(String beanName) {
        BeanDefinition definition = getBeanDefinition(beanName);
        List<String> postProcessorBeanNames = getBeanNamesByType(InstantiationAwareBeanPostProcessor.class);
        for (String processorBeanName : postProcessorBeanNames) {
            InstantiationAwareBeanPostProcessor postProcessor = getBeanByName(processorBeanName, InstantiationAwareBeanPostProcessor.class);
            if (postProcessor.isSupport(beanName)) {
                Object singleton = postProcessor.postProcessBeforeInstantiation(definition.getClass(), beanName);
                if (singleton != null) {
                    singleton = applyPostProcessAfterInstantiation(beanName, singleton);
                    return singleton;
                }
            }
        }
        return null;
    }

    private <T> T adaptBeanInstance(String beanName, Object singleton, Class<T> requireType) {
        if (requireType != null) {
            try {
                if (requireType.isInstance(singleton)) {
                    return (T) singleton;
                }else {
                    throw new RuntimeException("bean类型不匹配");
                }
            }catch (RuntimeException exception) {
                throw new RuntimeException("bean类型不匹配");
            }

        }
        return (T)singleton;
    }
}
