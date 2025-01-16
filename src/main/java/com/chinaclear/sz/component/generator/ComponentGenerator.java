package com.chinaclear.sz.component.generator;

import com.chinaclear.sz.component.common.DirectoryProcess;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.chinaclear.sz.component.util.GeneratorUtil;

public class ComponentGenerator extends AbstractGenerator {
    @Override
    public boolean isSupport(String moduleEnum) {
        return ModuleEnum.COMPONENT.getName().equals(moduleEnum);
    }


    @Override
    public void checkParam(ModuleInfo moduleInfo) {
        if (isBlank(moduleInfo.getSubProjectName())) {
            GeneratorUtil.showErrorMessage("子项目名不能为空");
            throw new RuntimeException("子项目名不能为空");
        }
    }

    @Override
    public void process(ModuleInfo moduleInfo) {
        DirectoryProcess directoryProcess = new DirectoryProcess("component");
        directoryProcess.process(moduleInfo);

        //更新settings.gradle文件
        super.updateSettingGradle(moduleInfo);
    }
}
