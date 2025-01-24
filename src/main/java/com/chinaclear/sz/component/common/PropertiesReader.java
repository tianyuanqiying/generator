package com.chinaclear.sz.component.common;

import cn.hutool.core.util.StrUtil;
import com.chinaclear.sz.component.config.PathCache;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;


/**
 * 负责解析properties文件，并返回键值对
 */
public class PropertiesReader implements IReader {

    /**
     * 读取配置
     * @param filePath
     * @return
     */
    public static Map<String, String> readConfig(String filePath) {
        Map<String, String> map = new HashMap<>();
        // 创建Properties对象
        Properties prop = new Properties();
        // 使用类加载器获取资源文件输入流
        try(InputStream inputStream = PathCache.class.getClassLoader().getResourceAsStream(filePath)) {
            prop.load(inputStream);

            // 获取属性值
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

    @Override
    public Map<String, String> readCache(String propertiesFileName) {
        //读取缓存配置；
        Map<String, String> configMap = readConfig(propertiesFileName);

        //路径匹配替换
        for (String key : configMap.keySet()) {
            String path = configMap.get(key);
            //替换掉${file.separator}
            String replacePath = path.replace("${file.separator}", File.separator);
            configMap.put(key, replacePath);
        }

        return configMap;
    }

}
