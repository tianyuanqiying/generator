package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.common.LifeCycle;
import com.chinaclear.sz.component.common.LifeCycleProcessor;
import com.chinaclear.sz.component.common.SmartLifeCycle;

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
        startLifeCycle();
    }

    private List<LifeCycle> getLifeCycles() {
        List<LifeCycle> lifeCycles = new ArrayList<>();
        List<String> lifeCycleBeanNames = beanFactory.getBeanNamesByType(LifeCycle.class);
        for (String cycleBeanName : lifeCycleBeanNames) {
            LifeCycle bean = beanFactory.getBean(cycleBeanName, LifeCycle.class);
            if (bean instanceof SmartLifeCycle) {
                lifeCycles.add(bean);
            }
        }
        return lifeCycles;
    }

    @Override
    public void end() {
        for (LifeCycle lifeCycle : getLifeCycles()) {
            lifeCycle.end();
        }
    }

    @Override
    public void isRunning() {

    }

    @Override
    public void onRefresh() {
        startLifeCycle();
    }

    private void startLifeCycle() {
        //触发回调start方法
        List<LifeCycle> lifeCycles = getLifeCycles();
        for (LifeCycle lifeCycle : lifeCycles) {
            lifeCycle.start();
        }
    }

    @Override
    public void onClose() {
        stopLifeCycles();
    }

    private void stopLifeCycles() {
        //调用LifeCycle#end方法
        List<LifeCycle> lifeCycles = getLifeCycles();
        for (LifeCycle lifeCycle : lifeCycles) {
            lifeCycle.end();
        }

    }
}