package com.chinaclear.sz.component.context;

import java.lang.reflect.Method;

public class BeanMethod {
    /**
     * 配置类里的@Bean方法
     */
    private Method method;

    /**
     * 配置类
     */
    private Class ConfigClazz;
    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class getConfigClazz() {
        return ConfigClazz;
    }

    public void setConfigClazz(Class configClazz) {
        ConfigClazz = configClazz;
    }
}
