package com.chinaclear.sz.component.generator;

import cn.hutool.core.collection.CollUtil;

import java.io.File;
import java.util.List;

public class QueryGenerator extends AbstractGenerator {
    @Override
    public boolean isSupport(String moduleEnum) {
        return ModuleEnum.QUERY.getName().equals(moduleEnum);
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

        //获取query公共目录
        java.lang.String queryDirPath = szDirPath
                + File.separator + "app"
                + File.separator + "query"
                + File.separator + moduleInfo.getId();

        //====目录名称======
        java.lang.String configDirPath = queryDirPath + File.separator + "config";
        java.lang.String modelDirPath = queryDirPath + File.separator + "model";
        java.lang.String serviceDirPath = queryDirPath + File.separator + "service";
        java.lang.String validationDirPath = queryDirPath + File.separator + "validation";
        java.lang.String serviceImplDirPath = serviceDirPath + File.separator + "impl";

        java.lang.String resourceDirPath = mainPath + File.separator + "resources";
        java.lang.String resourceConfigDirPath = resourceDirPath + File.separator + "config";
        java.lang.String testJavaPath = srcPath + File.separator + "test" + File.separator + "java";
        //====目录名称==========

        //创建目录
        List<java.lang.String> paths = CollUtil.newArrayList(configDirPath, modelDirPath, serviceDirPath, validationDirPath, serviceImplDirPath, resourceConfigDirPath, testJavaPath);
        for (java.lang.String path : paths) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        //更新setting.gradle
        super.updateSettingGradle(moduleInfo);
    }
}
