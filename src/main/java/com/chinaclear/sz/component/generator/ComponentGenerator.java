package com.chinaclear.sz.component.generator;

import cn.hutool.core.io.FileUtil;
import com.chinaclear.sz.component.pojo.CacheKey;
import com.chinaclear.sz.component.config.SingleCacheRegistry;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.chinaclear.sz.component.util.GeneratorUtil;

import java.util.List;

public class ComponentGenerator extends AbstractGenerator {
    @Override
    public boolean isSupport(String moduleEnum) {
        return ModuleEnum.COMPONENT.getName().equals(moduleEnum);
    }

    @Override
    public void generate(ModuleInfo moduleInfo) {
        if (isBlank(moduleInfo.getSubProjectName())) {
            GeneratorUtil.showErrorMessage("子项目名不能为空");
            throw new RuntimeException("子项目名不能为空");
        }

        //===组件公有目录===
        List<String> componentDirectories = SingleCacheRegistry.getCache(CacheKey.PATH).getWithPrefix("component");
        List<String> paths = super.handlePath(componentDirectories, moduleInfo);

        //创建目录
        for (String path : paths) {
            FileUtil.mkdir(path);
        }

        //更新settings.gradle文件
        super.updateSettingGradle(moduleInfo);
    }
}
