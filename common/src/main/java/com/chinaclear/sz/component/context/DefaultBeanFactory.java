package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.common.BeanDefinition;
import com.chinaclear.sz.component.common.BeanDefinitionRegistry;
import com.chinaclear.sz.component.common.BeanFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean工厂
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {
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
    public boolean containsBean(String name) {
        return singletonObjects.containsKey(name);
    }

    @Override
    public boolean getBeanNamesByType(Class type) {

        return false;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        if (!containsBean(beanName)) {
            beanDefinitionMap.put(beanName, beanDefinition);
        }
    }

    @Override
    public void removeBeanDefinition(String beanName) {

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return null;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }
}
