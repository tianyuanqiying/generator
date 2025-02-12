package com.chinaclear.sz.component.common;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);

    Object getSingleton(String beanName, boolean allowEarlyReference);
}
