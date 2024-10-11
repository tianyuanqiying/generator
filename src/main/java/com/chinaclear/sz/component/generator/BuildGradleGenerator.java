package com.chinaclear.sz.component.generator;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.chinaclear.sz.component.pojo.BuildTemplateParam;
import com.chinaclear.sz.component.pojo.ModuleEnum;
import com.chinaclear.sz.component.pojo.ModuleInfo;
import freemarker.template.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * build.gradle的生成器，支持process, query, param应用类型
 */
public class BuildGradleGenerator implements Generator {
    private BuildTemplateParam buildTemplateInfo;

    public void setBuildTemplateInfo(BuildTemplateParam buildTemplateInfo) {
        this.buildTemplateInfo = buildTemplateInfo;
    }

    public void generate() {
        if (Objects.isNull(buildTemplateInfo)) {
            return;
        }

        //根据应用类型拿到业务模板
        String templateName = "";
        if (StrUtil.equals(buildTemplateInfo.getMenuType(), ModuleEnum.PROCESS.getName())) {
            templateName = "build-process.ftl";
        }else if (StrUtil.equals(buildTemplateInfo.getMenuType(), ModuleEnum.PARAM.getName())) {
            templateName = "build-param.ftl";
        }else if (StrUtil.equals(buildTemplateInfo.getMenuType(), ModuleEnum.QUERY.getName())) {
            templateName = "build-query.ftl";
        }else if (StrUtil.equals(buildTemplateInfo.getMenuType(), ModuleEnum.COMPONENT.getName())) {
            templateName = "build-component.ftl";
        }


        //拿到build.gradle文件路径
        String buildGradleDirPath = buildTemplateInfo.getBaseRootDir()
                + File.separator + buildTemplateInfo.getSubProjectName();
        File targetDir = new File(buildGradleDirPath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        String buildGradleFilePath = buildGradleDirPath + File.separator + "build.gradle";
        File targetFile = new File(buildGradleFilePath);

        HashMap<String, Object> ftlParam = BeanUtil.toBean(buildTemplateInfo, HashMap.class);

        //创建模板替换符生成器， 替换模板变量，数据写入targetFile文件
        TemplateGenerator generator = new TemplateGenerator(templateName, ftlParam, targetFile);
        generator.generate();
    }

    @Override
    public boolean isSupport(String type) {
        return ModuleEnum.PROCESS.getName().equals(type)
                || ModuleEnum.PARAM.getName().equals(type)
                || ModuleEnum.QUERY.getName().equals(type)
                || ModuleEnum.COMPONENT.getName().equals(type);
    }

    @Override
    public void generate(ModuleInfo moduleInfo) {
        if (StrUtil.isBlank(moduleInfo.getVersion())) {
            throw new RuntimeException("version is null");
        }
        if (StrUtil.isBlank(moduleInfo.getSubProjectName())) {
            throw new RuntimeException("子项目名称为空");
        }
        if (StrUtil.isBlank(moduleInfo.getType())) {
            throw new RuntimeException("menuType is null");
        }

        BuildTemplateParam buildTemplateInfo = new BuildTemplateParam();
        buildTemplateInfo.setVersion(moduleInfo.getVersion());
        buildTemplateInfo.setSubProjectName(moduleInfo.getSubProjectName());
        buildTemplateInfo.setMenuType(moduleInfo.getType());
        buildTemplateInfo.setBaseRootDir(moduleInfo.getBaseDir());
        buildTemplateInfo.setConvertId(moduleInfo.getConvertId());
        this.setBuildTemplateInfo(buildTemplateInfo);

        this.generate();
    }

    private static class TemplateGenerator {
        private java.lang.String templateName;

        private Map<java.lang.String, Object> buildTemplateParam;

        private File targetFile;

        public TemplateGenerator(java.lang.String templateName,
                                 Map<java.lang.String, Object> buildTemplateParam,
                                 File targetFile) {
            this.templateName = templateName;
            this.buildTemplateParam = buildTemplateParam;
            this.targetFile = targetFile;
        }

        public void generate() {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            try {
                //设置模板目录
                cfg.setClassForTemplateLoading(BuildGradleGenerator.class, "/template");

                // 加载模板
                Template template = cfg.getTemplate(templateName);

                // 输出流，写入目标文件
                FileWriter fileWriter = new FileWriter(targetFile);

                //合并数据模型与模板， 替换模板参数
                template.process(buildTemplateParam, fileWriter);

                // 关闭输出流
                fileWriter.close();
            }  catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
