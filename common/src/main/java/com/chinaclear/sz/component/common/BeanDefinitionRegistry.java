package com.chinaclear.sz.component.common;

/**
 * Bean定义注册接口， 拥有该接口代表拥有注册Bean能力
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    void removeBeanDefinition(String beanName);

    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);
}
