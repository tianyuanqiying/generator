package com.chinaclear.sz.component.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.chinaclear.sz.component.pojo.BuildTemplateParam;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用实现文件模板替换流程
 */
public class CommonReplaceProcess extends AbstractReplaceProcess {
    String templateName;
    String targetPath;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    protected File getTargetFile(BuildTemplateParam param) {
        return new File(getTargetPath());
    }

    @Override
    protected Map<String, Object> getTemplateParam(BuildTemplateParam param) {
        HashMap<String, Object> ftlParam = BeanUtil.toBean(param, HashMap.class);
        return ftlParam;
    }

    @Override
    protected String getTemplateName(BuildTemplateParam param) {
        return getTemplateName();
    }

    public static void main(String[] args) {
        CommonReplaceProcess commonReplaceProcess = new CommonReplaceProcess();
        commonReplaceProcess.setParam(new BuildTemplateParam());
        commonReplaceProcess.setTemplateName("/comp/application_properties.ftl");
        commonReplaceProcess.setTargetPath("application_properties");
        commonReplaceProcess.templateReplace();
    }
}
