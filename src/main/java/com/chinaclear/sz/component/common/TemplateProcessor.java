package com.chinaclear.sz.component.common;

import cn.hutool.core.io.IoUtil;
import com.chinaclear.sz.component.generator.BuildGradleGenerator;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

/**
 * freemarker模板解析器
 */
public class TemplateProcessor {
    private String templateName;

    private Map<String, Object> buildTemplateParam;

    private File targetFile;

    public TemplateProcessor(String templateName,
                             Map<String, Object> buildTemplateParam,
                             File targetFile) {
        this.templateName = templateName;
        this.buildTemplateParam = buildTemplateParam;
        this.targetFile = targetFile;
    }

    public void generate() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        try(Writer writer = new StringWriter();
            BufferedWriter out = new BufferedWriter(new FileWriter(targetFile, Charset.forName("UTF-8")))) {
            //设置模板目录
            cfg.setClassForTemplateLoading(BuildGradleGenerator.class, "/template");
            cfg.setDefaultEncoding("UTF-8");
            // 加载模板
            Template template = cfg.getTemplate(templateName, "UTF-8");

            //合并数据模型与模板， 替换模板参数
            template.process(buildTemplateParam, writer);

            //写入文件
            out.write(writer.toString());
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}