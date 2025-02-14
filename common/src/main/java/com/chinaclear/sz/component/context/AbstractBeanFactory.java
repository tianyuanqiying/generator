package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.common.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AbstractBeanFactory implements SingletonBeanRegistry {
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


    @Override
    public Object getSingleton(String beanName) {
        return getSingleton(beanName, true);
    }

    @Override
    public Object getSingleton(String beanName, boolean allowEarlyReference) {
        return singletonObjects.get(beanName);
    }
}
