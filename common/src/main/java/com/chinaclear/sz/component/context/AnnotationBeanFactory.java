package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.common.BeanFactory;

public class AnnotationBeanFactory implements BeanFactory {

    @Override
    public <T> T getBeanByType(Class<T> classType) {
        return null;
    }

    @Override
    public Object getBeanByName(String beanName) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }
}
