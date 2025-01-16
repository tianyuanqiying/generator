package com.chinaclear.sz.component.common;

import com.chinaclear.sz.component.config.SingleCacheRegistry;
import com.chinaclear.sz.component.pojo.BuildTemplateParam;
import com.chinaclear.sz.component.pojo.CacheKey;
import com.chinaclear.sz.component.pojo.ModuleInfo;

import java.util.List;

public class FileProcess implements IProcess, PathResolver {
    private String keyPrefix;
    private BuildTemplateParam param;

    public FileProcess() {
    }

    public FileProcess(String keyPrefix, BuildTemplateParam param) {
        this.keyPrefix = keyPrefix;
        this.param = param;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public BuildTemplateParam getParam() {
        return param;
    }

    public void setParam(BuildTemplateParam param) {
        this.param = param;
    }

    @Override
    public void process(ModuleInfo moduleInfo) {
        //拿到模板路径缓存
        ICache templateMappingCache = SingleCacheRegistry.getCache(CacheKey.TEMPLATE_FILE_PATH_MAPPING);

        //拿到目标文件路径缓存
        ICache pathCache = SingleCacheRegistry.getCache(CacheKey.FILE_PATH);

        List<String> componentFileKeys = pathCache.getKeyWithPrefix(getKeyPrefix());

        for (String componentFileKey : componentFileKeys) {
            String templateFilePath = templateMappingCache.get(componentFileKey);
            String paramFilePath = pathCache.get(componentFileKey);
            String abstractTargetFilePath = handlePath(paramFilePath, moduleInfo);
            CommonReplaceProcess commonReplaceProcess = new CommonReplaceProcess();
            commonReplaceProcess.setParam(getParam());
            commonReplaceProcess.setTemplateName(templateFilePath);
            commonReplaceProcess.setTargetPath(abstractTargetFilePath);
            commonReplaceProcess.templateReplace();
        }
    }
}
