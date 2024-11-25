package com.chinaclear.sz.component.pojo;

public enum CacheKey {
    PATH("path.properties"),
    FILE_PATH("filepath.properties"),
    CONFIG("config.properties")
    ;
    String key;

    CacheKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
