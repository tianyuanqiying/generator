package com.chinaclear.sz.component.generator;


import com.chinaclear.sz.component.pojo.ModuleInfo;

/**
 *
 */
public interface Generator {
    /**
     * 是否支持该目录类型的生产呢个
     * @param type 目录类型
     * @return 是否支持
     */
    boolean isSupport(String type);

    /**
     * 生成目录
     * @param moduleInfo
     */
    void generate(ModuleInfo moduleInfo);


}
