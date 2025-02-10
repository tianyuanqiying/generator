package com.chinaclear.sz.component.common;

public interface BeanFactory {
    <T> T getBeanByType(Class<T> classType);

    Object getBeanByName(String beanName);

    boolean containsBean(String name);

    boolean getBeanNamesByType(Class type);
}
