package com.chinaclear.sz.component.util;

import com.chinaclear.sz.component.common.BeanDefinition;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 扫描包下的类信息
 */
public class ScannerUtil {
    public static List<Class> scan(String pkgPath) {
        if (pkgPath == null) {
            return new ArrayList<>();
        }
        List<Class> allClass = new ArrayList<>();

        //路径转换： com.xxx.xx => com/xxx/xx
        String replacePath = pkgPath.replace(".", "/");

        URL url = ScannerUtil.class.getClassLoader().getResource(replacePath);
        if (url == null) {
            System.out.println("路径不存在");
            return new ArrayList<>();
        }

        //扫描协议
        //扫描Jar包
        if (url.getProtocol().equalsIgnoreCase("jar")) {

        }

        //扫描file包
        if (url.getProtocol().equalsIgnoreCase("file")) {
            //拿到目录， 广度搜索类文件；
            File file = new File(url.getFile());
            allClass.addAll(scanFile(file, pkgPath));
        }

        return allClass;
    }

    /**
     * 扫描文件
     * @param file
     * @return
     */
    private static List<Class> scanFile(File file, String pkgPath) {
        List<Class> classList = new ArrayList<>();

        File[] files = file.listFiles();
        for (File subFile : files) {
            String fileName = subFile.getName();

            //若是目录，递归搜索；
            if (subFile.isDirectory()) {
                String newPkgPath = pkgPath + "." + fileName;
                classList.addAll(scanFile(subFile, newPkgPath));
            }

            //若是文件，加载类
            if (subFile.isFile() && subFile.getName().endsWith(".class")) {
                String className = fileName.substring(0, fileName.length() - 6);
                String classPath = pkgPath + "." + className;
                try {
                    Class<?> clazz = Class.forName(classPath);
                    classList.add(clazz);
                } catch (ClassNotFoundException e) {
                    System.out.println("加载失败路径为：" + classPath);
                }

            }
        }
        return classList;
    }
}
