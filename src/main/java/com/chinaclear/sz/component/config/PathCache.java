package com.chinaclear.sz.component.config;

import com.chinaclear.sz.component.common.AbstractCache;

import java.io.File;
import java.util.Map;

/**
 * 负责解析path.properties文件
 */
public class PathCache extends AbstractCache {
    private static final String PROPERTIES_FILE_PATH = "path.properties";

    @Override
    public void readCache() {
        Map<String, String> pathMaps = PropertiesReader.readConfig(PROPERTIES_FILE_PATH);

        for (String key : pathMaps.keySet()) {
            String path = pathMaps.get(key);
            String replacePath = path.replace("${file.separator}", File.separator);
            super.getCacheMap().put(key, replacePath);
        }
        System.out.println(cacheMap.size());
    }

}
