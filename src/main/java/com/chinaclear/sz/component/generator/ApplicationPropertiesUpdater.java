package com.chinaclear.sz.component.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.chinaclear.sz.component.config.SingleCacheRegistry;
import com.chinaclear.sz.component.pojo.CacheKey;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.chinaclear.sz.component.util.GeneratorUtil;

import java.util.HashSet;
import java.util.Set;

public class ApplicationPropertiesUpdater extends AbstractGenerator {
    Set<String> supportTypes = new HashSet<>();

    {
        supportTypes.add(ModuleEnum.QUERY.getName());
    }

    @Override
    public void checkParam(ModuleInfo moduleInfo) {
        if (CharSequenceUtil.isBlank(moduleInfo.getPackageName())) {
            GeneratorUtil.showErrorMessage("包名不能为空");
            throw new RuntimeException("包名不能为空");
        }
    }

    @Override
    public void process(ModuleInfo moduleInfo) {
        //当前子项目为application, 因此更改子项目名
        String originalSubProjectName = moduleInfo.getSubProjectName();
        moduleInfo.setSubProjectName("application");

        String applicationPropertiesFilePath = SingleCacheRegistry.getCache(CacheKey.PATH).get("backend_main_application_properties_path");
        String realApplicationPropertiesFilePath = super.handlePath(applicationPropertiesFilePath, moduleInfo);
        String insertLabel = "spring.config.import";
        String insertInfo = "  optional:classpath:" + moduleInfo.getPackageName() + "-query-application.properties,\\";
        //更新application.properties文件的insertLabel标签的下一行插入内容insertInfo
        super.insertFileInfo(insertLabel, insertInfo, realApplicationPropertiesFilePath);

        //子项目名还原
        moduleInfo.setSubProjectName(originalSubProjectName);
    }

    @Override
    public boolean isSupport(String type) {
        return supportTypes.contains(type);
    }
}
