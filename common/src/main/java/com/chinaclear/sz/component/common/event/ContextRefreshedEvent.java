package com.chinaclear.sz.component.common.event;

import com.chinaclear.sz.component.common.ApplicationContext;

public class ContextRefreshedEvent extends ApplicationEvent {
    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
