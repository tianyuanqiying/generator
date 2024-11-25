package com.chinaclear.sz.component.generator;

import cn.hutool.core.io.FileUtil;
import com.chinaclear.sz.component.common.PathResolver;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.chinaclear.sz.component.util.GeneratorUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public abstract class AbstractGenerator implements Generator, PathResolver {

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
            GeneratorUtil.showErrorMessage(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean isBlank(String text) {
        return text == null || text.trim().length() == 0;
    }

}
