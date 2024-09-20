package com.chinaclear.sz.component.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class AbstractGenerator implements Generator{

    /**
     * 更新setting.gradle文件
     * @param moduleInfo 模块名称
     */
    public void updateSettingGradle(ModuleInfo moduleInfo) {
        if(isBlank(moduleInfo.getName())) {
            return;
        }

        String inclusionInfo = "include '" + moduleInfo.getName() + "'";

        File settingsGradle = new File(moduleInfo.getBaseDir() + File.separator + "settings.gradle");

        try {
            if (!settingsGradle.exists()) {
                settingsGradle.createNewFile();
            }

            List<String> lines = FileUtil.readLines(settingsGradle, StandardCharsets.UTF_8);

            //include配置存在，不在添加
            for (String line : lines) {
                if(line.contains(inclusionInfo)) {
                    return;
                }
            }

            //插入配置
            FileUtil.appendUtf8String(System.lineSeparator(), settingsGradle);
            FileUtil.appendUtf8String(inclusionInfo, settingsGradle);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSrcPath(ModuleInfo moduleInfo) {
        //项目根目录
        String userDir = moduleInfo.getBaseDir();

        //加上子项目根目录
        String projectRootDir = isBlank(moduleInfo.getName()) ? userDir : userDir + File.separator + moduleInfo.getName();

        //返回项目的src目录
        return projectRootDir + File.separator + "src";
    }

    /**
     * 获取main目录
     * @param moduleName 子目录名称
     * @return main目录
     */
    public String getMainPath(ModuleInfo moduleName) {
        return getSrcPath(moduleName) + File.separator + "main";
    }

    /**
     * 获取公共目录sz的路径
     * @param moduleName 模块名称
     * @return sz 目录
     */
    public String getSzDirPath(ModuleInfo moduleName) {
        String mainPath = getMainPath(moduleName);

        return mainPath
                + File.separator + "java"
                + File.separator + "cn"
                + File.separator + "chinaclear"
                + File.separator + "sz";
    }

    public boolean isBlank(String text) {
        return text == null || text.trim().length() == 0;
    }
}
