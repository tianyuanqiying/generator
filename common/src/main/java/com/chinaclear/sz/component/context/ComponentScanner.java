package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.anno.Component;
import com.chinaclear.sz.component.anno.Configuration;
import com.chinaclear.sz.component.common.BeanDefinition;
import com.chinaclear.sz.component.common.IScanner;
import com.chinaclear.sz.component.util.ScannerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 扫描带有 @Component的类
 */
public class ComponentScanner implements IScanner {
    private List<Class> includeTypes = new ArrayList<>();

    public ComponentScanner() {
        includeTypes.add(Component.class);
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

            BeanDefinition definition = GenericBeanDefinition.buildBeanDefinition(clazz, BeanType.COMPONENT_TYPE);
            beanDefinitions.add(definition);

        }
        return beanDefinitions;
    }

    private boolean checkCandidate(Class clazz) {
        for (Class includeType : includeTypes) {
            return clazz.isAnnotationPresent(includeType);
        }
        return false;
    }


}
