package com.chinaclear.sz.component.common;

import java.util.Map;

/**
 * 解析缓存接口
 */
public interface IReader {
    /**
     * 读取缓存
     */
    Map<String, String> readCache(String propertiesFileName);
}
