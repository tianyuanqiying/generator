package com.chinaclear.sz.component.common;

import cn.hutool.core.io.FileUtil;
import com.chinaclear.sz.component.generator.AbstractGenerator;
import com.chinaclear.sz.component.pojo.BuildTemplateParam;

import java.io.File;
import java.util.Map;

public abstract class AbstractReplaceProcess {
    protected BuildTemplateParam param;

    public BuildTemplateParam getParam() {
        return param;
    }

    public void setParam(BuildTemplateParam param) {
        this.param = param;
    }

    /**
     * 获取目标文件的文件路径
     * @return 目标文件的文件路径
     */
    protected abstract File getTargetFile(BuildTemplateParam param);

    /**
     * 获取模板参数
     * @return 模板参数
     */
    protected abstract Map<String, Object> getTemplateParam(BuildTemplateParam param);

    protected abstract String getTemplateName(BuildTemplateParam param);

    /**
     * 生成流程
     * 1. 获取目标文件路径
     * 2. 获取模板参数
     * 3. 获取模板路径
     * 4. 执行模板替换
     * 5. 生成文件
     */
    public void templateReplace() {
        File targetFile = getTargetFile(param);

        FileUtil.mkParentDirs(targetFile);

        Map<String, Object> templateParam = getTemplateParam(param);

        String templateName = getTemplateName(param);

        TemplateProcessor generator = new TemplateProcessor(templateName, templateParam, targetFile);

        generator.generate();
    }
}
