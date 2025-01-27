package com.chinaclear.sz.component.common;

/**
 * 对Bean工厂进行加工接口
 */
public interface BeanFactoryPostProcessor{
    void postProcessBeanFactory(BeanFactory beanFactory);

}
