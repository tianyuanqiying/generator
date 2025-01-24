package com.chinaclear.sz.component.common;

import java.util.List;

/**
 * 缓存接口
 */
public interface ICache {
    /**
     * 初始化缓存
     */
    void initCache();

    /**
     * 根据key获取配置
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 根据key的前缀获取配置值
     * @param prefix key前缀
     * @return
     */
    List<String> getWithPrefix(String prefix);

    /**
     * 根据key的前缀 获取符合的所有key
     * @param prefix key的前缀
     * @return 所有key
     */
    List<String> getKeyWithPrefix(String prefix);
}
