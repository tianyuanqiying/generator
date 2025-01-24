package com.chinaclear.sz.component.config;

/**
 * 负责解析path.properties文件
 */
public class PathCache extends AbstractCache {
    private static final String PROPERTIES_FILE_PATH = "path.properties";
    @Override
    protected String getPropertiesFileName() {
        return PROPERTIES_FILE_PATH;
    }
}
