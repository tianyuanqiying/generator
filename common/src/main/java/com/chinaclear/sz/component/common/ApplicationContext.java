package com.chinaclear.sz.component.common;

/**
 * IOC容器：
 * 1. 支持IOC bean的注入
 * 2. 支持事件发布机制
 */
public interface ApplicationContext extends BeanFactory, ApplicationEventPublisher{
    /**
     * 刷新容器
     */
    void refresh();
}
