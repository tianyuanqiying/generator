package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.anno.Component;
import com.chinaclear.sz.component.common.BeanDefinition;
import com.chinaclear.sz.component.common.IScanner;
import com.chinaclear.sz.component.util.ScannerUtil;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 扫描注解Bean
 */
public class ClassPathScanner implements IScanner {
    private List<Class> includeTypes = new ArrayList<>();

    public ClassPathScanner() {
        includeTypes.add(Component.class);
    }

    @Override
    public List<BeanDefinition> scan(String path) {
        if (path == null) {
            return new ArrayList<>();
        }

        List<BeanDefinition> beanDefinitions = new ArrayList<>();

        //扫描包下的所有类
        List<Class> allClass = ScannerUtil.scan(path);

        //创建Bean信息
        for (Class clazz : allClass) {

            //检查条件
            if (!checkCandidate(clazz)) {
                continue;
            }

            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanName(clazz.getSimpleName());
            beanDefinition.setId(clazz.getSimpleName());
            beanDefinition.setBeanClass(clazz);
            beanDefinitions.add(beanDefinition);
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
        ClassPathScanner classPathScanner = new ClassPathScanner();
        List<BeanDefinition> com = classPathScanner.scan("com.chinaclear.sz");
        System.out.println(com);
    }

}
