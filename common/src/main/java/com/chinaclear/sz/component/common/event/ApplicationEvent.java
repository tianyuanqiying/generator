package com.chinaclear.sz.component.common.event;

public abstract class ApplicationEvent {
    private Object source;

    public ApplicationEvent(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return source;
    }
}
