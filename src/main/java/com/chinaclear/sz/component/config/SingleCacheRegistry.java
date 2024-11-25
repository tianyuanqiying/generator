package com.chinaclear.sz.component.config;

import com.chinaclear.sz.component.common.ICache;
import com.chinaclear.sz.component.pojo.CacheKey;

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
        PathCache pathCache = new PathCache();
        pathCache.readCache();
        cacheMap.put(CacheKey.PATH.getKey(), pathCache);

        ConfigCache configCache = new ConfigCache();
        configCache.readCache();
        cacheMap.put(CacheKey.CONFIG.getKey(), configCache);

        FilePathCache filePathCache = new FilePathCache();
        filePathCache.readCache();
        cacheMap.put(CacheKey.FILE_PATH.getKey(), filePathCache);
    }

    /**
     * 查询缓存文件，key为配置文件名
     * @return
     */
    public static ICache getCache(CacheKey cacheKey) {
        return cacheMap.get(cacheKey.getKey());
    }
}
