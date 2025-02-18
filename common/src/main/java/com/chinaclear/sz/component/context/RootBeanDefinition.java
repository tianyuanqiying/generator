package com.chinaclear.sz.component.context;

import java.lang.reflect.Method;

public class RootBeanDefinition {
    protected String id;
    protected Class beanClass;
    protected String beanName;
    protected BeanType beanType;
    private String methodName;
    private Method beanMethod;
    private Class configClass;
    private String factoryMethodName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public BeanType getBeanType() {
        return beanType;
    }

    public void setBeanType(BeanType beanType) {
        this.beanType = beanType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Method getBeanMethod() {
        return beanMethod;
    }

    public void setBeanMethod(Method beanMethod) {
        this.beanMethod = beanMethod;
    }

    public Class getConfigClass() {
        return configClass;
    }

    public void setConfigClass(Class configClass) {
        this.configClass = configClass;
    }

    public String getFactoryMethodName() {
        return factoryMethodName;
    }

    public void setFactoryMethodName(String factoryMethodName) {
        this.factoryMethodName = factoryMethodName;
    }
}
