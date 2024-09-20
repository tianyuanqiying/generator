package com.chinaclear.sz.component.generator;

import cn.hutool.core.collection.CollUtil;

import java.io.File;
import java.util.List;

/**
 * 参维目录生成器
 */
public class ParamGenerator extends AbstractGenerator{
    @Override
    public boolean isSupport(String moduleEnum) {
        return ModuleEnum.PARAM.getName().equals(moduleEnum);
    }

    @Override
    public void generate(ModuleInfo moduleInfo) {
        if (isBlank(moduleInfo.getId())) {
            throw new RuntimeException("id不能为空");
        }

        //获取src, main, sz目录
        java.lang.String srcPath = getSrcPath(moduleInfo);
        java.lang.String mainPath = getMainPath(moduleInfo);
        java.lang.String szDirPath = getSzDirPath(moduleInfo);

        //获取param公共目录
        java.lang.String paramDirPath = szDirPath
                + File.separator + "app"
                + File.separator + "process"
                + File.separator + "param"
                + File.separator + moduleInfo.getId();

        //===参维目录========
        java.lang.String constantDirPath = paramDirPath + File.separator + "constant";
        java.lang.String serviceDirPath = paramDirPath + File.separator + "service";
        java.lang.String serviceImplDirPath = serviceDirPath + File.separator + "impl";
        java.lang.String validationDirPath = paramDirPath + File.separator + "validation";
        java.lang.String commandDirPath = serviceImplDirPath + File.separator + "command";
        java.lang.String formDataDirPath = serviceImplDirPath + File.separator + "formdata";
        java.lang.String metaDataDirPath = serviceImplDirPath + File.separator + "metadata";

        java.lang.String resourceDirPath = mainPath + File.separator + "resources";
        java.lang.String resourceConfigPath = resourceDirPath + File.separator + "config";
        java.lang.String testJavaPath = srcPath + File.separator + "test" + File.separator + "java";
        //===参维目录========

        List<java.lang.String> paths = CollUtil.newArrayList(constantDirPath, serviceDirPath, validationDirPath, commandDirPath, formDataDirPath, metaDataDirPath, resourceDirPath, resourceConfigPath, testJavaPath);

        //创建目录
        for (java.lang.String path : paths) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        super.updateSettingGradle(moduleInfo);
    }
}
