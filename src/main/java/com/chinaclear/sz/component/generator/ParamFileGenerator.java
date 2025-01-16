package com.chinaclear.sz.component.generator;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.chinaclear.sz.component.common.FileProcess;
import com.chinaclear.sz.component.pojo.BuildTemplateParam;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.intellij.openapi.ui.Messages;

import java.util.Date;
import java.util.List;

public class ParamFileGenerator extends AbstractGenerator {
    @Override
    public boolean isSupport(String type) {
        return ModuleEnum.PARAM.getName().equals(type);
    }

    @Override
    public void checkParam(ModuleInfo moduleInfo) {
        if (StrUtil.isBlank(moduleInfo.getSubProjectName())) {
            Messages.showErrorDialog("子项目不能为空", "错误提示");
            throw new RuntimeException("子项目不能为空");
        }

        if (StrUtil.isBlank(moduleInfo.getType())) {
            Messages.showErrorDialog("菜单类型不能为空", "错误提示");
            throw new RuntimeException("菜单类型不能为空");
        }

        if (StrUtil.isBlank(moduleInfo.getComponentName())) {
            Messages.showErrorDialog("组件名不能为空", "错误提示");
            throw new RuntimeException("组件名不能为空");
        }
    }

    @Override
    public void process(ModuleInfo moduleInfo) {
        //模板参数
        BuildTemplateParam buildTemplateInfo = new BuildTemplateParam();
        buildTemplateInfo.setSubProjectName(moduleInfo.getSubProjectName());
        buildTemplateInfo.setBaseRootDir(moduleInfo.getBaseDir());
        buildTemplateInfo.setPackageName(moduleInfo.getPackageName());
        buildTemplateInfo.setAuthorName(moduleInfo.getAuthorName());
        buildTemplateInfo.setCreateDate(DateUtil.formatDate(new Date()));
        buildTemplateInfo.setComponentName(moduleInfo.getComponentName());
        buildTemplateInfo.setComponentCnName(moduleInfo.getComponentCnName());

        FileProcess fileProcess = new FileProcess();
        fileProcess.setKeyPrefix("param");
        fileProcess.setParam(buildTemplateInfo);
        fileProcess.process(moduleInfo);
    }
}
