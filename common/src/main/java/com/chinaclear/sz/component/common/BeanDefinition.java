package com.chinaclear.sz.component.common;

import com.chinaclear.sz.component.context.BeanType;

public interface BeanDefinition {
    String getBeanName();

    Class getBeanClass();

    BeanType getBeanType();
}
