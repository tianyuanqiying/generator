package com.chinaclear.sz.component.config;

/**
 * 负责解析config.properties文件
 */
public class ConfigCache extends AbstractCache {
    private static final String PROPERTIES_FILE_PATH = "config.properties";

    @Override
    protected String getPropertiesFileName() {
        return PROPERTIES_FILE_PATH;
    }
}
