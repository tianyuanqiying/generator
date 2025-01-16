package com.chinaclear.sz.component.generator;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.chinaclear.sz.component.common.CommonReplaceProcess;
import com.chinaclear.sz.component.common.FileProcess;
import com.chinaclear.sz.component.common.GeneratorConstant;
import com.chinaclear.sz.component.common.ICache;
import com.chinaclear.sz.component.config.SingleCacheRegistry;
import com.chinaclear.sz.component.pojo.BuildTemplateParam;
import com.chinaclear.sz.component.pojo.CacheKey;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.chinaclear.sz.component.util.GeneratorUtil;
import com.intellij.openapi.ui.Messages;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组件初始化文件
 */
public class ComponentFileGenerator extends AbstractGenerator {
    @Override
    public boolean isSupport(String type) {
        return ModuleEnum.COMPONENT.getName().equals(type);
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

        if (Boolean.FALSE.equals(Character.isUpperCase(moduleInfo.getComponentName().charAt(0)))) {
            Messages.showErrorDialog("组件名称遵循大驼峰，首字母需要大写", "错误提示");
            throw new RuntimeException("组件名尊重大驼峰，首字母需要大写");
        }
    }

    @Override
    public void process(ModuleInfo moduleInfo) {
        //模板参数
        BuildTemplateParam buildTemplateInfo = new BuildTemplateParam();
        buildTemplateInfo.setVersion(moduleInfo.getVersion());
        buildTemplateInfo.setSubProjectName(moduleInfo.getSubProjectName());
        buildTemplateInfo.setMenuType(moduleInfo.getType());
        buildTemplateInfo.setBaseRootDir(moduleInfo.getBaseDir());
        buildTemplateInfo.setConvertId(moduleInfo.getConvertId());
        buildTemplateInfo.setPackageName(moduleInfo.getPackageName());
        buildTemplateInfo.setAuthorName(moduleInfo.getAuthorName());
        buildTemplateInfo.setCreateDate(DateUtil.formatDate(new Date()));
        buildTemplateInfo.setComponentName(moduleInfo.getComponentName());
        //大驼峰转换为中划线
        buildTemplateInfo.setKebabCaseComponentName(GeneratorUtil.toKebabCase(moduleInfo.getComponentName()));
        buildTemplateInfo.setComponentCnName(moduleInfo.getComponentCnName());

        FileProcess fileProcess = new FileProcess();
        fileProcess.setKeyPrefix("component");
        fileProcess.setParam(buildTemplateInfo);
        fileProcess.process(moduleInfo);

    }
}
