package com.chinaclear.sz.component.generator;

import cn.hutool.core.io.FileUtil;
import com.chinaclear.sz.component.common.DirectoryProcess;
import com.chinaclear.sz.component.pojo.CacheKey;
import com.chinaclear.sz.component.config.SingleCacheRegistry;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.chinaclear.sz.component.util.GeneratorUtil;

import java.util.List;

public class ProcessGenerator extends AbstractGenerator {
    @Override
    public boolean isSupport(String moduleEnum) {
        return ModuleEnum.PROCESS.getName().equals(moduleEnum);
    }

    @Override
    public void checkParam(ModuleInfo moduleInfo) {
        if (isBlank(moduleInfo.getSubProjectName())) {
            GeneratorUtil.showErrorMessage("子项目名称不能为空");
            throw new RuntimeException("子项目名称不能为空");

        }
        if (isBlank(moduleInfo.getPackageName())) {
            GeneratorUtil.showErrorMessage("包名不能为空");
            throw new RuntimeException("包名不能为空");
        }
    }

    @Override
    public void process(ModuleInfo moduleInfo) {
        DirectoryProcess directoryProcess = new DirectoryProcess("process");
        directoryProcess.process(moduleInfo);


        //处理settings.gradle文件
        super.updateSettingGradle(moduleInfo);
        super.updateApplicationBuildGradle(moduleInfo);
    }
}
