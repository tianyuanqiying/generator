package com.chinaclear.sz.component.generator;

import cn.hutool.core.collection.CollUtil;
import com.chinaclear.sz.component.config.PathCache;
import com.chinaclear.sz.component.config.PropertiesCache;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;

import java.io.File;
import java.util.List;

public class ComponentGenerator extends AbstractGenerator {
    @Override
    public boolean isSupport(String moduleEnum) {
        return ModuleEnum.COMPONENT.getName().equals(moduleEnum);
    }

    @Override
    public void generate(ModuleInfo moduleInfo) {
        if (isBlank(moduleInfo.getSubProjectName())) {
            throw new RuntimeException("id不能为空");
        }

        //获取src, main, sz目录
//        String srcPath = getSrcPath(moduleInfo);
//        String mainPath = getMainPath(moduleInfo);
//        String szDirPath = getSzDirPath(moduleInfo);
//
//        //组件父目录
//        String componentDirPath = szDirPath
//                + File.separator + "component"
//                + File.separator + moduleInfo.getSubProjectName();

        //===组件公有目录===
        List<String> componentDirectories = PathCache.getWithPrefix("component");
        List<String> paths = super.handlePath(componentDirectories, moduleInfo);
//
//        String daotaDirPath = componentDirPath + File.separator + "dao";
//        String dtotaDirPath = componentDirPath + File.separator + "dto";
//        String modelDirPath = componentDirPath + File.separator + "model";
//        String serviceDirPath = componentDirPath + File.separator + "service";
//        String temlateDirPath = componentDirPath + File.separator + "template";
//        String serviceImplDirPath = serviceDirPath + File.separator + "impl";
//
//        String resourceDirPath = mainPath + File.separator + "resources";
//        String testJavaPath = srcPath + File.separator + "test" + File.separator + "java";
//

//        List<String> paths = CollUtil.newArrayList(daotaDirPath, dtotaDirPath, modelDirPath, serviceDirPath, temlateDirPath, serviceImplDirPath, resourceDirPath, testJavaPath);

        //创建目录
        for (String path : paths) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        //更新settings.gradle文件
        super.updateSettingGradle(moduleInfo);
    }
}
