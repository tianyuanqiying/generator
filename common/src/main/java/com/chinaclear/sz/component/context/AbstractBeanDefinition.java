package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.common.BeanDefinition;

import java.util.List;

public class AbstractBeanDefinition implements BeanDefinition {
    protected String id;
    protected Class beanClass;
    protected String beanName;
    protected BeanType beanType;
    private List<String> initMethods;
    public String getId() {
        return id;
    }

    public List<String> getInitMethods() {
        return initMethods;
    }

    public void setInitMethods(List<String> initMethods) {
        this.initMethods = initMethods;
    }

    public BeanType getBeanType() {
        return beanType;
    }

    public void setBeanType(BeanType beanType) {
        this.beanType = beanType;
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

}
