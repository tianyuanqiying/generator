package com.chinaclear.sz.component.config;

import com.chinaclear.sz.component.common.AbstractCache;

import java.util.Map;

/**
 * 负责解析config.properties文件
 */
public class ConfigCache extends AbstractCache {
    private static final String PROPERTIES_FILE_PATH = "config.properties";

    @Override
    public void readCache() {
        Map<String, String> configMaps = PropertiesReader.readConfig(PROPERTIES_FILE_PATH);
        super.getCacheMap().putAll(configMaps);
        System.out.println(super.getCacheMap().size());
    }
}
