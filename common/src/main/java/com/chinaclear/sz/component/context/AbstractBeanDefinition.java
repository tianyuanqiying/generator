package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.common.BeanDefinition;

public class AbstractBeanDefinition implements BeanDefinition {
    protected String id;
    protected Class beanClass;
    protected String beanName;

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
}
