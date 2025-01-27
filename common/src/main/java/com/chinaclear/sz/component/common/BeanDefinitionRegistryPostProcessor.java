package com.chinaclear.sz.component.common;

public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor{
    void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry);
}
