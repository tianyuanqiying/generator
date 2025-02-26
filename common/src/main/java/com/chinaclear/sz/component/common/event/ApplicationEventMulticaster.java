package com.chinaclear.sz.component.common.event;

public interface ApplicationEventMulticaster {
    void addApplicationListener(ApplicationListener<?> applicationListener);

    void removeApplicationListener(ApplicationListener<?> applicationListener);

    void multicastEvent(ApplicationEvent event);
}
