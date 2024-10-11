package com.chinaclear.sz.component.config;

import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.io.InputStream;
import java.util.*;

/**
 * 负责解析config.properties文件
 */
public class PathCache {
    private static final String PROPERTIES_FILE_PATH = "path.properties";

    private static Map<String, String> map = new HashMap<>();

    static {
        readConfig();
    }

    public static void readConfig() {
        Map<String, String> pathMaps = PropertiesReader.readConfig(PROPERTIES_FILE_PATH);

        for (String key : pathMaps.keySet()) {
            String path = pathMaps.get(key);
            String replacePath = path.replace("${file.separator}", File.separator);
            map.put(key, replacePath);
        }
        System.out.println(map.size());
    }

    public static String get(String key) {
        return map.get(key);
    }

    /**
     * 根据前缀获取目录集合
     * @param prefix 前缀
     * @return 目录集合
     */
    public static List<String> getWithPrefix(String prefix) {
        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (key.startsWith(prefix)) {
                list.add(map.get(key));
            }
        }
        return list;
    }
    public static void main(String[] args) {
        PathCache.readConfig();
        System.out.println(PathCache.map);
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
