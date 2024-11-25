package com.chinaclear.sz.component.generator;

import cn.hutool.core.io.FileUtil;
import com.chinaclear.sz.component.pojo.CacheKey;
import com.chinaclear.sz.component.config.SingleCacheRegistry;
import com.chinaclear.sz.component.pojo.ModuleInfo;

import java.util.List;

/**
 * 子项目根目录下的document目录生成器
 */
public class DocumentGenerator extends AbstractGenerator{
    @Override
    public boolean isSupport(String type) {
        return true;
    }

    @Override
    public void generate(ModuleInfo moduleInfo) {
        //1. 获取document配置路径
        List<String> documents = SingleCacheRegistry.getCache(CacheKey.PATH).getWithPrefix("document");

        //2. 拼接生成document的真实路径
        List<String> realDocumentPaths = super.handlePath(documents, moduleInfo);

        for (String documentPath : realDocumentPaths) {
            if (FileUtil.exist(documentPath)) {
                continue;
            }

            FileUtil.mkdir(documentPath);
        }
    }
}
