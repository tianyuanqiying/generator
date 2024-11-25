package com.chinaclear.sz.component.generator;

import cn.hutool.core.io.FileUtil;
import com.chinaclear.sz.component.pojo.CacheKey;
import com.chinaclear.sz.component.config.SingleCacheRegistry;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.chinaclear.sz.component.util.GeneratorUtil;

import java.util.List;

public class QueryGenerator extends AbstractGenerator {
    @Override
    public boolean isSupport(String moduleEnum) {
        return ModuleEnum.QUERY.getName().equals(moduleEnum);
    }

    @Override
    public void generate(ModuleInfo moduleInfo) {
        if (isBlank(moduleInfo.getSubProjectName())) {
            GeneratorUtil.showErrorMessage("子项目名不能为空");
            throw new RuntimeException("子项目名不能为空");
        }

        List<String> queryDirectories = SingleCacheRegistry.getCache(CacheKey.PATH).getWithPrefix("query");
        List<String> paths = super.handlePath(queryDirectories, moduleInfo);

        for (String path : paths) {
            FileUtil.mkdir(path);
        }

        //更新setting.gradle
        super.updateSettingGradle(moduleInfo);
    }
}
