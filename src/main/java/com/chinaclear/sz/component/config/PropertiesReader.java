package com.chinaclear.sz.component.config;

import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;


public final class PropertiesReader {

    public static Map<String, String> readConfig(String filePath) {
        Map<String, String> map = new HashMap<>();
        // 创建Properties对象
        Properties prop = new Properties();
        // 使用类加载器获取资源文件输入流
        try(InputStream inputStream = PathCache.class.getClassLoader().getResourceAsStream(filePath)) {
            prop.load(inputStream);

            // 获取属性值, 只读取component, query, process, param开头key
            for (Map.Entry<Object, Object> entry : prop.entrySet()) {
                if (Objects.isNull(entry.getValue()) || StrUtil.isBlankIfStr(entry.getValue())) {
                    continue;
                }
                map.put((String) entry.getKey(), entry.getValue().toString());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
