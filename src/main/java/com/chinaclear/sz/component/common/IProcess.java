package com.chinaclear.sz.component.common;

import com.chinaclear.sz.component.pojo.ModuleInfo;

/**
 * 文件处理流程接口
 */
public interface IProcess {
    /**
     * 执行文件处理流程
     * @param moduleInfo
     */
    void process(ModuleInfo moduleInfo);
}
