package com.chinaclear.sz.component.pojo;

import com.chinaclear.sz.component.config.ConfigCache;
import com.chinaclear.sz.component.config.FilePathCache;
import com.chinaclear.sz.component.config.PathCache;
import com.chinaclear.sz.component.config.TemplateAndPathMappingCache;

public enum CacheKey {
    PATH("path.properties", PathCache.class),
    FILE_PATH("filepath.properties", FilePathCache.class),
    CONFIG("config.properties", ConfigCache.class),
    TEMPLATE_FILE_PATH_MAPPING("templateandfilepathmapping.properties", TemplateAndPathMappingCache.class)
    ;
    String key;
    Class cacheClazz;

    CacheKey(String key, Class cacheClazz) {
        this.key = key;
        this.cacheClazz = cacheClazz;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Class getCacheClazz() {
        return cacheClazz;
    }

    public void setCacheClazz(Class cacheClazz) {
        this.cacheClazz = cacheClazz;
    }
}
