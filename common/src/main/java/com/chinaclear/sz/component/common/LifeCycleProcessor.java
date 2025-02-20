package com.chinaclear.sz.component.common;

public interface LifeCycleProcessor extends LifeCycle {
    void onRefresh();

    void onClose();
}
