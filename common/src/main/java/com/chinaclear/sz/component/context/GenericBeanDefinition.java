package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.common.BeanDefinition;

public class GenericBeanDefinition extends AbstractBeanDefinition {
    public static BeanDefinition buildBeanDefinition(Class clazz, BeanType beanType) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(clazz);
        beanDefinition.setBeanName(clazz.getSimpleName());
        beanDefinition.setId(clazz.getSimpleName());
        beanDefinition.setBeanType(beanType);
        return beanDefinition;
    }
}
