package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.common.*;

import java.util.List;

public class AnnotationApplicationContext implements ApplicationContext {
    private String basePackage;
    private ConfigurableListableBeanFactory beanFactory;

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
    public <T> T getBeanByName(String beanName, Class<T> classType) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    @Override
    public List<String> getBeanNamesByType(Class type) {
        return beanFactory.getBeanNamesByType(type);
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
        finishBeanFactoryInitialization(beanFactory);

        //扩展
        finishRefresh();
    }

    private void finishRefresh() {

    }

    private void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.preInstantiateSingletons();
    }

    private void registerApplicationListener() {

    }

    private void initApplicationContextPublisher() {

    }

    private void registerBeanPostProcessors(BeanFactory beanFactory) {

    }

    private void invokeBeanFactoryPostProcessors(BeanFactory beanFactory) {
        List<String> beanNames = beanFactory.getBeanNamesByType(BeanFactoryPostProcessor.class);
        for (String beanName : beanNames) {
            BeanFactoryPostProcessor beanFactoryPostProcessor = beanFactory.getBeanByName(beanName, BeanFactoryPostProcessor.class);
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }

        //扩展二次处理BeanFactoryPostProcessor
    }
}
