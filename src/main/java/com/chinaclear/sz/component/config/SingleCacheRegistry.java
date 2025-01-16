package com.chinaclear.sz.component.config;

import com.chinaclear.sz.component.common.ICache;
import com.chinaclear.sz.component.pojo.CacheKey;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 单例配置缓存池
 */
public class SingleCacheRegistry {
    public static Map<String, ICache> cacheMap = new HashMap<>();

    static {
        initCache();
    }

    private static void initCache() {
        for (CacheKey value : CacheKey.values()) {
            try {
                Constructor constructor = value.getCacheClazz().getConstructor();
                ICache instance = (ICache) constructor.newInstance();
                instance.readCache();
                cacheMap.put(value.getKey(), instance);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 查询缓存文件，key为配置文件名
     * @return
     */
    public static ICache getCache(CacheKey cacheKey) {
        return cacheMap.get(cacheKey.getKey());
    }
}
