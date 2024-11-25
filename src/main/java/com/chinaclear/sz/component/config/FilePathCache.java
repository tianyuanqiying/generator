package com.chinaclear.sz.component.config;


import com.chinaclear.sz.component.common.AbstractCache;

import java.io.File;
import java.util.Map;

/**
 * filepath.properties缓存
 */
public class FilePathCache extends AbstractCache {

    private static final String FILE_PATH = "filepath.properties";

    @Override
    public void readCache() {
        Map<String, String> configMap = PropertiesReader.readConfig(FILE_PATH);
        for (String key : configMap.keySet()) {
            String path = configMap.get(key);
            String replacePath = path.replace("${file.separator}", File.separator);
            super.getCacheMap().put(key, replacePath);
        }
        System.out.println(super.getCacheMap().size());
    }
}
