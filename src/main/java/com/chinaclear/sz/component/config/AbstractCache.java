package com.chinaclear.sz.component.config;

import com.chinaclear.sz.component.common.ICache;
import com.chinaclear.sz.component.common.PropertiesReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存抽象类，存放文件缓存的基础操作
 */
public abstract class AbstractCache implements ICache {
    protected Map<String, String> cacheMap = new HashMap<>();

    public Map<String, String> getCacheMap() {
        return cacheMap;
    }

    public void setCacheMap(Map<String, String> cacheMap) {
        this.cacheMap = cacheMap;
    }

    @Override
    public String get(String key) {
        return getCacheMap().get(key);
    }

    @Override
    public List<String> getWithPrefix(String prefix) {
        List<String> list = new ArrayList<>();
        for (String key : getCacheMap().keySet()) {
            if (key.startsWith(prefix)) {
                list.add(getCacheMap().get(key));
            }
        }
        return list;
    }

    @Override
    public List<String> getKeyWithPrefix(String prefix) {
        List<String> list = new ArrayList<>();
        for (String key : getCacheMap().keySet()) {
            if (key.startsWith(prefix)) {
                list.add(key);
            }
        }
        return list;
    }

    @Override
    public void initCache() {
        PropertiesReader propertiesReader = new PropertiesReader();
        Map<String, String> propertiesCache = propertiesReader.readCache(getPropertiesFileName());
        getCacheMap().putAll(propertiesCache);
    }

    protected abstract String getPropertiesFileName();
}
