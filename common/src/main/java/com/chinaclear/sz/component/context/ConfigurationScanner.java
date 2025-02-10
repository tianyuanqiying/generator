package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.anno.Component;
import com.chinaclear.sz.component.anno.Configuration;
import com.chinaclear.sz.component.common.BeanDefinition;
import com.chinaclear.sz.component.common.IScanner;
import com.chinaclear.sz.component.util.ScannerUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 扫描带有@Configuration类 和@Bean方法
 */
public class ConfigurationScanner implements IScanner {
    private List<Class> includeTypes = new ArrayList<>();

    public List<BeanMethod> getBeanMethods() {
        return beanMethods;
    }

    public void setBeanMethods(List<BeanMethod> beanMethods) {
        this.beanMethods = beanMethods;
    }

    private List<BeanMethod> beanMethods = new ArrayList<>();

    public ConfigurationScanner() {
        includeTypes.add(Configuration.class);
    }

    @Override
    public List<BeanDefinition> scan(String path) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();

        //扫描包下的所有类
        List<Class> allClass = ScannerUtil.scan(path);

        //创建Bean信息
        for (Class clazz : allClass) {
            //检查条件
            if (!checkCandidate(clazz)) {
                continue;
            }

            BeanDefinition definition = GenericBeanDefinition.buildBeanDefinition(clazz, BeanType.CONFIGURATION_TYPE);
            beanDefinitions.add(definition);

            //扫描@Bean方法
            BeanAnnoParser beanAnnoParser = new BeanAnnoParser();
            List<Method> methods = beanAnnoParser.scan(clazz);
            for (Method method : methods) {
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setConfigClazz(clazz);
                beanMethod.setMethod(method);
                beanMethods.add(beanMethod);
            }
        }

        return beanDefinitions;
    }

    private boolean checkCandidate(Class clazz) {
        for (Class includeType : includeTypes) {
            return clazz.isAnnotationPresent(includeType);
        }
        return false;
    }

    public static void main(String[] args) {
        ConfigurationScanner classPathScanner = new ConfigurationScanner();
        List<BeanDefinition> com = classPathScanner.scan("com.chinaclear.sz");
        System.out.println(com);
    }

}
