package com.chinaclear.sz.component.common;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.chinaclear.sz.component.pojo.BuildTemplateParam;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用实现
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
        File file = new File("src/main/resources/template/comp/data.sql");
        File file1 = new File("data.sql");
        FileUtil.copy(file, file1, true);
    }
}
