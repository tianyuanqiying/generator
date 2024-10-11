package com.chinaclear.sz.component.generator;

import cn.hutool.core.collection.CollUtil;
import com.chinaclear.sz.component.config.PathCache;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;

import java.io.File;
import java.util.List;

public class QueryGenerator extends AbstractGenerator {
    @Override
    public boolean isSupport(String moduleEnum) {
        return ModuleEnum.QUERY.getName().equals(moduleEnum);
    }

    @Override
    public void generate(ModuleInfo moduleInfo) {
        if (isBlank(moduleInfo.getSubProjectName())) {
            throw new RuntimeException("id不能为空");
        }

        List<String> queryDirectories = PathCache.getWithPrefix("query");
        List<String> paths = super.handlePath(queryDirectories, moduleInfo);
//        //获取src, main, sz目录
//        String srcPath = getSrcPath(moduleInfo);
//        String mainPath = getMainPath(moduleInfo);
//        String szDirPath = getSzDirPath(moduleInfo);
//
//        //获取query公共目录
//        String queryDirPath = szDirPath
//                + File.separator + "app"
//                + File.separator + "query"
//                + File.separator + moduleInfo.getPackageName();
//
//        //====目录名称======
//        String configDirPath = queryDirPath + File.separator + "config";
//        String modelDirPath = queryDirPath + File.separator + "model";
//        String serviceDirPath = queryDirPath + File.separator + "service";
//        String validationDirPath = queryDirPath + File.separator + "validation";
//        String serviceImplDirPath = serviceDirPath + File.separator + "impl";
//
//        String resourceDirPath = mainPath + File.separator + "resources";
//        String resourceConfigDirPath = resourceDirPath + File.separator + "config";
//        String testJavaPath = srcPath + File.separator + "test" + File.separator + "java";
//        //====目录名称==========

        //创建目录
//        List<String> paths = CollUtil.newArrayList(configDirPath, modelDirPath, serviceDirPath, validationDirPath, serviceImplDirPath, resourceConfigDirPath, testJavaPath);
        for (String path : paths) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        //更新setting.gradle
        super.updateSettingGradle(moduleInfo);
    }
}
