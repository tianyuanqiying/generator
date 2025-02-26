package com.chinaclear.sz.component.common.event;

import com.chinaclear.sz.component.common.BeanFactory;
import com.chinaclear.sz.component.common.ConfigurableListableBeanFactory;

import java.util.ArrayList;
import java.util.List;

public class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster{
    private List<ApplicationListener<?>> listeners;
    private ConfigurableListableBeanFactory beanFactory;

    public List<ApplicationListener<?>> getListeners() {
        return listeners;
    }

    public void setListeners(List<ApplicationListener<?>> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> applicationListener) {
        if (listeners == null) {
            List<ApplicationListener<?>> applicationListeners = getApplicationListeners();
            this.setListeners(applicationListeners);
        }

        listeners.add(applicationListener);
    }

    private List<ApplicationListener<?>> getApplicationListeners() {
        List<ApplicationListener<?>> listeners = new ArrayList<>();
        List<String> applicationListenerNames = beanFactory.getBeanNamesByType(ApplicationListener.class);
        for (String applicationListenerName : applicationListenerNames) {
            ApplicationListener applicationListener = beanFactory.getBeanByName(applicationListenerName, ApplicationListener.class);
            listeners.add(applicationListener);
        }
        return listeners;
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> applicationListener) {
        if (listeners == null) {
            return;
        }
        listeners.remove(applicationListener);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        
    }
}
