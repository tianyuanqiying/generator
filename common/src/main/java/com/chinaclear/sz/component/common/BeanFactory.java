package com.chinaclear.sz.component.common;

import java.util.List;

public interface BeanFactory {
    <T> T getBeanByType(Class<T> classType);

    Object getBeanByName(String beanName);
    <T> T getBeanByName(String beanName, Class<T> classType);

    boolean containsBean(String name);

    List<String> getBeanNamesByType(Class type);

    Object getBean(String beanName);
}
