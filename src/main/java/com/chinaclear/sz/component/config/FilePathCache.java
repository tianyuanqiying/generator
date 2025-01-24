package com.chinaclear.sz.component.config;


/**
 * filepath.properties缓存
 */
public class FilePathCache extends AbstractCache {
    private static final String FILE_PATH = "filepath.properties";

    @Override
    protected String getPropertiesFileName() {
        return FILE_PATH;
    }
}
