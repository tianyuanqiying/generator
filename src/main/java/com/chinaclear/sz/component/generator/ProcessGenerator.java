package com.chinaclear.sz.component.generator;

import cn.hutool.core.collection.CollUtil;
import com.chinaclear.sz.component.config.PathCache;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;

import java.io.File;
import java.util.List;

public class ProcessGenerator extends AbstractGenerator {
    @Override
    public boolean isSupport(String moduleEnum) {
        return ModuleEnum.PROCESS.getName().equals(moduleEnum);
    }

    @Override
    public void generate(ModuleInfo moduleInfo) {
        if (isBlank(moduleInfo.getSubProjectName())) {
            throw new RuntimeException("子项目名称不能为空");
        }
        if (isBlank(moduleInfo.getPackageName())) {
            throw new RuntimeException("包名不能为空");
        }
        List<String> processDirectories = PathCache.getWithPrefix("process");
        List<String> paths = super.handlePath(processDirectories, moduleInfo);
        //获取src, main, sz目录
//        String srcPath = getSrcPath(moduleInfo);
//        String mainPath = getMainPath(moduleInfo);
//        String szDirPath = getSzDirPath(moduleInfo);
//
//        //获取流程公共父目录；
//        String processDirPath = szDirPath
//                + File.separator + "app"
//                + File.separator + "process"
//                + File.separator + moduleInfo.getPackageName();
//
//        //获取流程目录
//        String configDirPath = processDirPath + File.separator + "config";
//        String constantDirPath = processDirPath + File.separator + "constant";
//        String controllerDirPath = processDirPath + File.separator + "controller";
//        String listenerDirPath = processDirPath + File.separator + "listener";
//        String modelDirPath = processDirPath + File.separator + "model";
//        String serviceImplDirPath = processDirPath + File.separator + "service" + File.separator + "impl";
//        String validatorDirPath = processDirPath + File.separator + "validation";
//
//        String commandDirPath = serviceImplDirPath + File.separator + "command";
//        String jobDirPath = serviceImplDirPath + File.separator + "job";
//        String noticeBaseTimeDirPath = serviceImplDirPath + File.separator + "noticebasetime";
//        String relateDataDirPath = serviceImplDirPath + File.separator + "relatedata";
//        String validDirPath = serviceImplDirPath + File.separator + "valid";
//
//        String resourceDirPath = mainPath + File.separator + "resources";
//        String resourceConfigPath = resourceDirPath + File.separator + "config";
//        String workFlowDirPath = resourceDirPath + File.separator + "workflow";
//        String testJavaPath = srcPath + File.separator + "test" + File.separator + "java";
//        //=======流程应用目录===============
//
//        List<String> paths = CollUtil.newArrayList(configDirPath, constantDirPath, controllerDirPath, listenerDirPath, modelDirPath, serviceImplDirPath, validatorDirPath,
//                commandDirPath, jobDirPath, noticeBaseTimeDirPath, relateDataDirPath, validDirPath,
//                resourceConfigPath, workFlowDirPath, testJavaPath);

        //创建目录
        for (String path : paths) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        super.updateSettingGradle(moduleInfo);
    }
}
