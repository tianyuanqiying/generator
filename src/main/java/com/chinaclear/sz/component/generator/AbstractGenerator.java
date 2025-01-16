package com.chinaclear.sz.component.generator;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import com.chinaclear.sz.component.common.ICheck;
import com.chinaclear.sz.component.common.IProcess;
import com.chinaclear.sz.component.common.PathResolver;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.chinaclear.sz.component.util.GeneratorUtil;
import com.intellij.openapi.ui.Messages;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractGenerator implements Generator, PathResolver, IProcess, ICheck {
    @Override
    public void generate(ModuleInfo moduleInfo) {
        //校验参数
        checkParam(moduleInfo);

        //调用文件处理流程： 修改文件， 生成文件/目录
        process(moduleInfo);
    }

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

    public void updateApplicationBuildGradle(ModuleInfo moduleInfo) {
        if(isBlank(moduleInfo.getSubProjectName())) {
            return;
        }

        String insertInfo = "    implementation project(':" + moduleInfo.getSubProjectName() + "')";
        String depenciesLabel = "dependencies";
        Integer insertLineNum = null;

        File buildGradle = new File(moduleInfo.getBaseDir() + File.separator + "application" + File.separator +"build.gradle");

        try {
            if (!buildGradle.exists()) {
                buildGradle.createNewFile();
            }

            List<String> lines = FileUtil.readLines(buildGradle, StandardCharsets.UTF_8);
            //检查是否配置了dependencies标签
            for (int i = 0; i < lines.size(); i++) {
                if(lines.get(i).contains(depenciesLabel)){
                    insertLineNum = i;
                    break;
                }
            }
            if(Objects.isNull(insertLineNum)){
                Messages.showErrorDialog("applicatin下的build.gradle文件并未配置" + depenciesLabel, "错误提示");
                return;
            }
            //检查是否已存在目标配置
            List<String> list = lines.stream().filter(p -> p.contains(insertInfo)).collect(Collectors.toList());
            if(!CollUtil.isEmpty(list)){
                return;
            }
            //插入目标数据
            lines.add(insertLineNum + 1,insertInfo);
            FileUtil.writeLines(lines,buildGradle, CharsetUtil.CHARSET_UTF_8);
        } catch (IOException e) {
            GeneratorUtil.showErrorMessage(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean isBlank(String text) {
        return text == null || text.trim().length() == 0;
    }

}
