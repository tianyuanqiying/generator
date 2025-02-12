package com.chinaclear.sz.component.common;

/**
 * 实例化回调；
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    default Object postProcessBeforeInstantiation(Class beanClass, String beanName) {
        return null;
    }

    default boolean postProcessAfterInstantiation(Object bean, String beanName) {
        return true;
    }

    default Object postProcessProperties(Object pvs, Object bean, String beanName) {
        return pvs;
    }
}
