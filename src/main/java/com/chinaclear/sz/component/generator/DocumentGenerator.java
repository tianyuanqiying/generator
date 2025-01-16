package com.chinaclear.sz.component.generator;

import com.chinaclear.sz.component.common.DirectoryProcess;
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
    public void checkParam(ModuleInfo moduleInfo) {

    }

    @Override
    public void process(ModuleInfo moduleInfo) {
        DirectoryProcess directoryProcess = new DirectoryProcess("document");
        directoryProcess.process(moduleInfo);
    }
}
