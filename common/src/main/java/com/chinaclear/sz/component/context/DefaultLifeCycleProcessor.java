package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.common.LifeCycle;
import com.chinaclear.sz.component.common.LifeCycleProcessor;

import java.util.ArrayList;
import java.util.List;

public class DefaultLifeCycleProcessor implements LifeCycleProcessor {
    private DefaultBeanFactory beanFactory;

    public DefaultBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(DefaultBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void start() {
        getLifeCycles();


    }

    private List<LifeCycle> getLifeCycles() {
        List<String> lifeCycleBeanNames = beanFactory.getBeanNamesByType(LifeCycle.class);

        List<LifeCycle> lifeCycles = new ArrayList<>();

        for (String cycleBeanName : lifeCycleBeanNames) {

            LifeCycle bean = beanFactory.getBean(cycleBeanName, LifeCycle.class);
        }
        return null;
    }

    @Override
    public void end() {

    }

    @Override
    public void isRunning() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClose() {

    }
}
