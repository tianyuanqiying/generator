package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.common.ApplicationContext;
import com.chinaclear.sz.component.common.ApplicationEvent;
import com.chinaclear.sz.component.common.BeanFactory;

public class AnnotationApplicationContext implements ApplicationContext {
    private String basePackage;
    private BeanFactory beanFactory;

    public AnnotationApplicationContext() {
    }

    public AnnotationApplicationContext(String basePackage) {
        this.basePackage = basePackage;
        this.beanFactory = new DefaultBeanFactory();
        this.refresh();
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
    }

    @Override
    public <T> T getBeanByType(Class<T> classType) {
        return beanFactory.getBeanByType(classType);
    }

    @Override
    public Object getBeanByName(String beanName) {
        return beanFactory.getBeanByName(beanName);
    }

    @Override
    public boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    @Override
    public void refresh() {
        //完成Bean的扫描
        invokeBeanFactoryPostProcessors(beanFactory);

        //完成后置处理器的注册
        registerBeanPostProcessors(beanFactory);

        //初始化事件发布器
        initApplicationContextPublisher();

        //注册事件监听器
        registerApplicationListener();

        //创建并初始化Bean
        finishBeanFactoryInitialization();

        //扩展
        finishRefresh();
    }

    private void finishRefresh() {

    }

    private void finishBeanFactoryInitialization() {

    }

    private void registerApplicationListener() {

    }

    private void initApplicationContextPublisher() {

    }

    private void registerBeanPostProcessors(BeanFactory beanFactory) {

    }

    private void invokeBeanFactoryPostProcessors(BeanFactory beanFactory) {

    }
}
