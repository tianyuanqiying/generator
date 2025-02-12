package com.chinaclear.sz.component.config;

import java.io.File;
import java.io.InputStream;
import java.util.*;

/**
 * 负责解析config.properties文件
 */
public class PropertiesCache {
    private static final String PROPERTIES_FILE_PATH = "config.properties";

    private static Map<String, String> map = new HashMap<>();

    static {
        readConfig();
    }

    public static void readConfig() {
        Map<String, String> configMaps = PropertiesReader.readConfig(PROPERTIES_FILE_PATH);
        map.putAll(configMaps);
        System.out.println(map.size());
    }

    public static String get(String key) {
        return map.get(key);
    }
}
