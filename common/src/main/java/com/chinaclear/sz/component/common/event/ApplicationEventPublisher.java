package com.chinaclear.sz.component.common.event;

import com.chinaclear.sz.component.common.event.ApplicationEvent;

public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
