package com.chinaclear.sz.component.common;

import com.chinaclear.sz.component.pojo.ModuleInfo;

/**
 * 参数校验接口
 */
public interface ICheck {
    /**
     * 参数校验
     * @param moduleInfo
     */
    void checkParam(ModuleInfo moduleInfo);
}
