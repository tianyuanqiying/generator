package com.chinaclear.sz.component.common;

import java.util.List;

/**
 * 扫描器
 */
public interface IScanner {
    List<BeanDefinition> scan(String path);
}
