package com.chinaclear.sz.component.generator;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.chinaclear.sz.component.common.AbstractReplaceProcess;
import com.chinaclear.sz.component.pojo.BuildTemplateParam;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.chinaclear.sz.component.util.GeneratorUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * build.gradle的生成器，支持process, query, param应用类型
 */
public class BuildGradleGenerator extends AbstractReplaceProcess implements Generator {
    @Override
    protected File getTargetFile(BuildTemplateParam param) {
        //拿到build.gradle文件路径
        String buildGradleDirPath = param.getBaseRootDir()
                + File.separator + param.getSubProjectName();
        File targetDir = new File(buildGradleDirPath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        String buildGradleFilePath = buildGradleDirPath + File.separator + "build.gradle";
        File targetFile = new File(buildGradleFilePath);

        return targetFile;
    }

    @Override
    protected Map<String, Object> getTemplateParam(BuildTemplateParam param) {
        HashMap<String, Object> ftlParam = BeanUtil.toBean(param, HashMap.class);
        return ftlParam;
    }

    @Override
    protected String getTemplateName(BuildTemplateParam param) {
        //根据应用类型拿到业务模板
        String templateName = "";
        if (StrUtil.equals(param.getMenuType(), ModuleEnum.PROCESS.getName())) {
            templateName = "build-process.ftl";
        }else if (StrUtil.equals(param.getMenuType(), ModuleEnum.PARAM.getName())) {
            templateName = "build-param.ftl";
        }else if (StrUtil.equals(param.getMenuType(), ModuleEnum.QUERY.getName())) {
            templateName = "build-query.ftl";
        }else if (StrUtil.equals(param.getMenuType(), ModuleEnum.COMPONENT.getName())) {
            templateName = "build-component.ftl";
        }
        return templateName;
    }

    @Override
    public boolean isSupport(String type) {
        return ModuleEnum.PROCESS.getName().equals(type)
                || ModuleEnum.PARAM.getName().equals(type)
                || ModuleEnum.QUERY.getName().equals(type)
                || ModuleEnum.COMPONENT.getName().equals(type);
    }

    @Override
    public void generate(ModuleInfo moduleInfo) {
        if (StrUtil.isBlank(moduleInfo.getVersion())) {
            GeneratorUtil.showErrorMessage("版本号不能为空");
            return;
        }
        if (StrUtil.isBlank(moduleInfo.getSubProjectName())) {
            GeneratorUtil.showErrorMessage("子项目名称为空");
            return;
        }
        if (StrUtil.isBlank(moduleInfo.getType())) {
            GeneratorUtil.showErrorMessage("菜单类型不能为空");
            return;
        }

        BuildTemplateParam buildTemplateInfo = new BuildTemplateParam();
        buildTemplateInfo.setVersion(moduleInfo.getVersion());
        buildTemplateInfo.setSubProjectName(moduleInfo.getSubProjectName());
        buildTemplateInfo.setMenuType(moduleInfo.getType());
        buildTemplateInfo.setBaseRootDir(moduleInfo.getBaseDir());
        buildTemplateInfo.setConvertId(moduleInfo.getConvertId());
        this.setParam(buildTemplateInfo);

        //执行模板替换流程，生成build.gradle文件
        this.templateReplace();
    }

}
