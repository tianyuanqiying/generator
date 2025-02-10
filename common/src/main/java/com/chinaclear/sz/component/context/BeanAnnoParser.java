package com.chinaclear.sz.component.context;

import com.chinaclear.sz.component.anno.Bean;
import com.chinaclear.sz.component.common.BeanDefinition;
import com.chinaclear.sz.component.common.IScanner;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BeanAnnoParser {
    private List<Class> includeTypes = new ArrayList<>();
    public BeanAnnoParser() {
        includeTypes.add(Bean.class);
    }

    public List<Method> scan(Class clazz) {
        List<Method> beanMethodList = new ArrayList<>();

        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if (!checkCandidate(declaredMethod))  {
                continue;
            }
            beanMethodList.add(declaredMethod);
        }

        return beanMethodList;
    }

    private boolean checkCandidate(AccessibleObject declaredMethod) {
        for (Class includeType : includeTypes) {
            if (declaredMethod.isAnnotationPresent(includeType)) {
                return true;
            }
        }
        return false;
    }
}
