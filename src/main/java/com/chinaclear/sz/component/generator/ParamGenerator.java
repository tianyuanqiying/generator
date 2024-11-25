package com.chinaclear.sz.component.generator;

import cn.hutool.core.io.FileUtil;
import com.chinaclear.sz.component.pojo.CacheKey;
import com.chinaclear.sz.component.config.SingleCacheRegistry;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.chinaclear.sz.component.util.GeneratorUtil;

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
            GeneratorUtil.showErrorMessage("子项目名不能为空");
            throw new RuntimeException("子项目名不能为空");
        }

        //路径处理
        List<String> paramDirectories = SingleCacheRegistry.getCache(CacheKey.PATH).getWithPrefix("param");
        List<String> paths = handlePath(paramDirectories, moduleInfo);

        //创建目录
        for (String path : paths) {
            FileUtil.mkdir(path);
        }

        //更新settings.gradle文件
        super.updateSettingGradle(moduleInfo);
    }
}
