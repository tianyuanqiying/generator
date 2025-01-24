package com.chinaclear.sz.component.common;

import cn.hutool.core.io.FileUtil;
import com.chinaclear.sz.component.config.SingleCacheRegistry;
import com.chinaclear.sz.component.pojo.CacheKey;
import com.chinaclear.sz.component.pojo.ModuleInfo;

import java.util.List;

/**
 * 目录生成流程
 */
public class DirectoryProcess implements IProcess, PathResolver{
    private String keyPrefix;

    public DirectoryProcess(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    @Override
    public void process(ModuleInfo moduleInfo) {
        //===公有目录===
        List<String> componentDirectories = SingleCacheRegistry.getCache(CacheKey.PATH).getWithPrefix(getKeyPrefix());
        List<String> paths = handlePath(componentDirectories, moduleInfo);

        //创建目录
        for (String path : paths) {
            FileUtil.mkdir(path);
        }
    }
}
