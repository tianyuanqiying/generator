package com.chinaclear.sz.component.context;

import java.lang.reflect.Method;

public class BeanMethodBeanDefinition extends AbstractBeanDefinition{
    private String methodName;
    private Method beanMethod;
    private Class configClass;

    public static BeanMethodBeanDefinition buildBeanDefinition(BeanMethod beanMethod) {
        BeanMethodBeanDefinition beanMethodBeanDefinition = new BeanMethodBeanDefinition();
        beanMethodBeanDefinition.methodName = beanMethod.getMethod().getName();
        beanMethodBeanDefinition.beanMethod = beanMethod.getMethod();
        beanMethodBeanDefinition.setId(beanMethod.getMethod().getName());
        beanMethodBeanDefinition.setBeanName(beanMethod.getMethod().getName());
        beanMethodBeanDefinition.setBeanType(BeanType.BEAN_METHOD_TYPE);
        beanMethodBeanDefinition.configClass = beanMethod.getConfigClazz();
        return beanMethodBeanDefinition;
    }
}
