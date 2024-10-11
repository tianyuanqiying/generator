package com.chinaclear.sz.component.generator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.chinaclear.sz.component.pojo.ModuleInfo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGenerator implements Generator{

    /**
     * 更新setting.gradle文件
     * @param moduleInfo 模块名称
     */
    public void updateSettingGradle(ModuleInfo moduleInfo) {
        if(isBlank(moduleInfo.getSubProjectName())) {
            return;
        }

        String inclusionInfo = "include '" + moduleInfo.getSubProjectName() + "'";

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

    public boolean isBlank(String text) {
        return text == null || text.trim().length() == 0;
    }

    /**
     * 相对路径处理为绝对路径
     * @param directories 目录
     * @param moduleInfo 变量
     * @return 绝对路径
     */
    protected List<String> handlePath(List<String> directories, ModuleInfo moduleInfo) {
        List<String> realPaths = new ArrayList<>();
        if (CollUtil.isEmpty(directories)) {
            return realPaths;
        }

        for (String directory : directories) {
            //替换掉替换符 ${packageName};
            String path = directory.replace("${packageName}", moduleInfo.getPackageName());

            //避免开头配置不清楚，统一加上File.separator
            if (!path.startsWith(File.separator)) {
                path = File.separator + path;
            }

            //目录路径 = 基础目录路径 + 子项目名称 + path
            String realPath = moduleInfo.getBaseDir() + File.separator + moduleInfo.getSubProjectName() + path;
            realPaths.add(realPath);
        }
        return realPaths;
    }
}
