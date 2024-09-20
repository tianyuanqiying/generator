package com.chinaclear.sz.component.generator;

import cn.hutool.core.collection.CollUtil;

import java.io.File;
import java.util.List;

public class ProcessGenerator extends AbstractGenerator {
    @Override
    public boolean isSupport(String moduleEnum) {
        return ModuleEnum.PROCESS.getName().equals(moduleEnum);
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

        //获取流程公共父目录；
        java.lang.String processDirPath = szDirPath
                + File.separator + "app"
                + File.separator + "process"
                + File.separator + moduleInfo.getId();

        //获取流程目录
        java.lang.String configDirPath = processDirPath + File.separator + "config";
        java.lang.String constantDirPath = processDirPath + File.separator + "constant";
        java.lang.String controllerDirPath = processDirPath + File.separator + "controller";
        java.lang.String listenerDirPath = processDirPath + File.separator + "listener";
        java.lang.String modelDirPath = processDirPath + File.separator + "model";
        java.lang.String serviceImplDirPath = processDirPath + File.separator + "service" + File.separator + "impl";
        java.lang.String validatorDirPath = processDirPath + File.separator + "validation";

        java.lang.String commandDirPath = serviceImplDirPath + File.separator + "command";
        java.lang.String jobDirPath = serviceImplDirPath + File.separator + "job";
        java.lang.String noticeBaseTimeDirPath = serviceImplDirPath + File.separator + "noticebasetime";
        java.lang.String relateDataDirPath = serviceImplDirPath + File.separator + "relatedata";
        java.lang.String validDirPath = serviceImplDirPath + File.separator + "valid";

        java.lang.String resourceDirPath = mainPath + File.separator + "resources";
        java.lang.String resourceConfigPath = resourceDirPath + File.separator + "config";
        java.lang.String workFlowDirPath = resourceDirPath + File.separator + "workflow";
        java.lang.String testJavaPath = srcPath + File.separator + "test" + File.separator + "java";
        //=======流程应用目录===============

        List<java.lang.String> paths = CollUtil.newArrayList(configDirPath, constantDirPath, controllerDirPath, listenerDirPath, modelDirPath, serviceImplDirPath, validatorDirPath,
                commandDirPath, jobDirPath, noticeBaseTimeDirPath, relateDataDirPath, validDirPath,
                resourceConfigPath, workFlowDirPath, testJavaPath);

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
