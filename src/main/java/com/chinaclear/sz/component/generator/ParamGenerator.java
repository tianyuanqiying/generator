package com.chinaclear.sz.component.generator;

import cn.hutool.core.collection.CollUtil;
import com.chinaclear.sz.component.config.PathCache;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;

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
        if (isBlank(moduleInfo.getSubProjectName())) {
            throw new RuntimeException("id不能为空");
        }

        List<String> paramDirectories = PathCache.getWithPrefix("param");
        List<String> paths = handlePath(paramDirectories, moduleInfo);

//        //获取src, main, sz目录
//        String srcPath = getSrcPath(moduleInfo);
//        String mainPath = getMainPath(moduleInfo);
//        String szDirPath = getSzDirPath(moduleInfo);
//
//        //获取param公共目录
//        String paramDirPath = szDirPath
//                + File.separator + "app"
//                + File.separator + "process"
//                + File.separator + "param"
//                + File.separator + moduleInfo.getPackageName();
//
//        //===参维目录========
//        String constantDirPath = paramDirPath + File.separator + "constant";
//        String serviceDirPath = paramDirPath + File.separator + "service";
//        String serviceImplDirPath = serviceDirPath + File.separator + "impl";
//        String validationDirPath = paramDirPath + File.separator + "validation";
//        String commandDirPath = serviceImplDirPath + File.separator + "command";
//        String formDataDirPath = serviceImplDirPath + File.separator + "formdata";
//        String metaDataDirPath = serviceImplDirPath + File.separator + "metadata";
//
//        String resourceDirPath = mainPath + File.separator + "resources";
//        String resourceConfigPath = resourceDirPath + File.separator + "config";
//        String testJavaPath = srcPath + File.separator + "test" + File.separator + "java";
//        //===参维目录========
//
//        List<String> paths = CollUtil.newArrayList(constantDirPath, serviceDirPath, validationDirPath, commandDirPath, formDataDirPath, metaDataDirPath, resourceDirPath, resourceConfigPath, testJavaPath);

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
