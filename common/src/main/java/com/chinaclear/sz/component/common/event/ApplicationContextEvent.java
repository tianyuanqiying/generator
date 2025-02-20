package com.chinaclear.sz.component.common.event;

import com.chinaclear.sz.component.common.ApplicationContext;

public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }

    public ApplicationContext getApplicationContext() {
        return (ApplicationContext) super.getSource();
    }
}
