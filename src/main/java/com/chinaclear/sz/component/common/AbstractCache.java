package com.chinaclear.sz.component.common;

import com.chinaclear.sz.component.common.ICache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
