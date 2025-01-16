package com.chinaclear.sz.component.config;

import com.chinaclear.sz.component.common.AbstractCache;

import java.io.File;
import java.util.Map;

/**
 * 解析templateandfilepathmapping.properties文件
 */
public class TemplateAndPathMappingCache extends AbstractCache {

    @Override
    public void readCache() {
        Map<String, String> mappingConfigs = PropertiesReader.readConfig("templateandfilepathmapping.properties");
        for (String key : mappingConfigs.keySet()) {
            String path = mappingConfigs.get(key);
            String replacePath = path.replace("${file.separator}", File.separator);
            super.getCacheMap().put(key, replacePath);
        }
        System.out.println(super.getCacheMap().size());
    }
}
