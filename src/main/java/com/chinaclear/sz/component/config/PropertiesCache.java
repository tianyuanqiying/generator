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

    public static void main(String[] args) {
        PropertiesCache.readConfig();
        System.out.println(PropertiesCache.map);
        List<String> component = PathCache.getWithPrefix("component");
        System.out.println();
        String path = component.get(0);
        String demo = path.replace("${packageName}", "demo").replace("${file.separator}", File.separator);
        File file = new File(demo);
        System.out.println(file.exists());
        file.mkdirs();
        System.out.println(file.exists());
    }
}