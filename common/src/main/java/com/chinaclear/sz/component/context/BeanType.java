package com.chinaclear.sz.component.context;

/**
 * Bean的注入类型
 */
public enum BeanType {
    COMPONENT_TYPE(1),
    CONFIGURATION_TYPE(2),
    BEAN_METHOD_TYPE(3),
    FACTORY_BEAN_TYPE(4),
    SYSTEM_TYPE(5)
    ;
    public Integer type;
    BeanType(Integer type) {
        this.type = type;
    }

}
