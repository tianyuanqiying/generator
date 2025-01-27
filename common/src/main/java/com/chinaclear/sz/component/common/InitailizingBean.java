package com.chinaclear.sz.component.common;

/**
 * Bean创建初始化
 */
public interface InitailizingBean {
    /**
     * Bean初始化回调
     */
    void afterPropertiesSet();
}
