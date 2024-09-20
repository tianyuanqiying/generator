package com.chinaclear.sz.component.generator;

import cn.hutool.core.collection.CollUtil;

import java.io.File;
import java.util.List;

public class ComponentGenerator extends AbstractGenerator {
    @Override
    public boolean isSupport(String moduleEnum) {
        return ModuleEnum.COMPONENT.getName().equals(moduleEnum);
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

        //组件父目录
        java.lang.String componentDirPath = szDirPath
                + File.separator + "component"
                + File.separator + moduleInfo.getId();

        //===组件公有目录===
        java.lang.String daotaDirPath = componentDirPath + File.separator + "dao";
        java.lang.String dtotaDirPath = componentDirPath + File.separator + "dto";
        java.lang.String modelDirPath = componentDirPath + File.separator + "model";
        java.lang.String serviceDirPath = componentDirPath + File.separator + "service";
        java.lang.String temlateDirPath = componentDirPath + File.separator + "template";
        java.lang.String serviceImplDirPath = serviceDirPath + File.separator + "impl";

        java.lang.String resourceDirPath = mainPath + File.separator + "resources";
        java.lang.String testJavaPath = srcPath + File.separator + "test" + File.separator + "java";

        List<java.lang.String> paths = CollUtil.newArrayList(daotaDirPath, dtotaDirPath, modelDirPath, serviceDirPath, temlateDirPath, serviceImplDirPath, resourceDirPath, testJavaPath);

        //创建目录
        for (java.lang.String path : paths) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        //更新settings.gradle文件
        super.updateSettingGradle(moduleInfo);
    }
}
