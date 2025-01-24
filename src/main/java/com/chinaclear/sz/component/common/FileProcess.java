package com.chinaclear.sz.component.common;

import com.chinaclear.sz.component.config.SingleCacheRegistry;
import com.chinaclear.sz.component.pojo.BuildTemplateParam;
import com.chinaclear.sz.component.pojo.CacheKey;
import com.chinaclear.sz.component.pojo.ModuleInfo;

import java.util.List;

/**
 * 根据文件模板映射，生成文件的流程
 */
public class FileProcess implements IProcess, PathResolver {
    /**
     * key前缀
     */
    private String keyPrefix;
    /**
     * 模板参数
     */
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

        //从filePath.properties文件中拿到以keyPrefix开头的key
        List<String> componentFileKeys = pathCache.getKeyWithPrefix(getKeyPrefix());

        for (String componentFileKey : componentFileKeys) {
            //从templateandfilepathmapping.properties拿到componentFileKey的值， 即模板文件ftl的路径
            String templateFilePath = templateMappingCache.get(componentFileKey);

            //从filePath.properties文件中拿到componentFileKey的值
            String paramFilePath = pathCache.get(componentFileKey);

            //处理文件路径，拿到绝对路径， 拿到生成文件的绝对路径
            String abstractTargetFilePath = handlePath(paramFilePath, moduleInfo);

            //根据模板文件， 生成的目标文件， 模板参数， 模板替换并生成文件；
            CommonReplaceProcess commonReplaceProcess = new CommonReplaceProcess();
            commonReplaceProcess.setParam(getParam());
            commonReplaceProcess.setTemplateName(templateFilePath);
            commonReplaceProcess.setTargetPath(abstractTargetFilePath);
            commonReplaceProcess.templateReplace();
        }
    }
}
