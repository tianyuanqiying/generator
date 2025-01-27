package com.chinaclear.sz.component.common;

public interface BeanPostProcessor {
    /**
     * 是否支持Bean的处理
     * @param bean
     * @return
     */
    default boolean isSupport(Object bean) {
        return true;
    }

    /**
     * 初始化前操作
     * @param bean
     */
    void postProcessBeforeBeanInitialization(Object bean);

    /**
     * 初始化后操作
     * @param bean
     */
    void postProcessAfterBeanInitialization(Object bean);
}
