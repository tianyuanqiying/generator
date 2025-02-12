package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.common.*;

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
        return null;
    }

    public <T> T getBean(String beanName, Class<T> requireType) {
        //Bean的生命周期
        //1. 实例化前；
        //2. 实例化Bean
        //3. 实例化后
        // 循环依赖下的AOP动态代理
        //4. 自动注入
        // 处理Aware回调
        //5. 初始化前
        //6. 初始化
        //7. 初始化后
        //8. 所有单例Bean创建结束
        Object singleton = getSingleton(beanName);
        if (singleton != null) {

        } else {
            
        }
        return adaptBeanInstance(beanName, singleton, requireType);
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
