package com.chinaclear.sz.component.generator;

import cn.hutool.core.text.CharSequenceUtil;
import com.chinaclear.sz.component.config.SingleCacheRegistry;
import com.chinaclear.sz.component.pojo.CacheKey;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.chinaclear.sz.component.util.GeneratorUtil;

import java.util.HashSet;
import java.util.Set;

public class ApplicationBuildGradleUpdater extends AbstractGenerator{
    Set<String> supportTypes = new HashSet<>();

    {
        supportTypes.add(ModuleEnum.PARAM.getName());
        supportTypes.add(ModuleEnum.QUERY.getName());
        supportTypes.add(ModuleEnum.PROCESS.getName());
    }

    @Override
    public void checkParam(ModuleInfo moduleInfo) {
    }

    @Override
    public void process(ModuleInfo moduleInfo) {
        //当前子项目为application, 因此更改子项目名
        String originalSubProjectName = moduleInfo.getSubProjectName();
        moduleInfo.setSubProjectName("application");
        String applicationBuildGradlePath = SingleCacheRegistry.getCache(CacheKey.PATH).get("backend_main_application_build_gradle_path");
        String realApplicationBuildGradlePath = super.handlePath(applicationBuildGradlePath, moduleInfo);
        String insertInfo = "    implementation project(':" + originalSubProjectName + "')";
        String insertLabel = "dependencies";
        super.insertFileInfo(insertLabel, insertInfo, realApplicationBuildGradlePath);

        //子项目名还原
        moduleInfo.setSubProjectName(originalSubProjectName);
    }

    @Override
    public boolean isSupport(String type) {
        return supportTypes.contains(type);
    }
}
