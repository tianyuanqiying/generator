package com.chinaclear.sz.component.generator;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.chinaclear.sz.component.common.CommonReplaceProcess;
import com.chinaclear.sz.component.common.GeneratorConstant;
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
    public void generate(ModuleInfo moduleInfo) {
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
            Messages.showErrorDialog("组件名称尊重大驼峰，首字母需要大写", "错误提示");
            throw new RuntimeException("组件名尊重大驼峰，首字母需要大写");
        }

        //拿到组件初始化文件的文件路径
        List<String> componentFilePaths = SingleCacheRegistry.getCache(CacheKey.FILE_PATH).getWithPrefix(GeneratorConstant.COMPONENT_PREFIX);

        //相对路径转绝对路径
        List<String> absoluteFilePaths = handlePath(componentFilePaths, moduleInfo);
        List<String> templateFilePaths = absoluteFilePaths.stream().filter(path -> path.endsWith(GeneratorConstant.JAVA_SUFFIX)).toList();

        //路径与模板名的映射关系
        Map<String, String> filePathAndTemplateNameMap = new HashMap<>();
        for (String absoluteFilePath : templateFilePaths) {
            String fileName = getFileName(absoluteFilePath);
            String replaceFilePath = absoluteFilePath.replaceAll(GeneratorConstant.COMPONENT_NAME_REPLACEMENT, moduleInfo.getComponentName());
            String templateFilePath =  "/comp/" + fileName + GeneratorConstant.FTL_SUFFIX;
            filePathAndTemplateNameMap.put(replaceFilePath, templateFilePath);
        }

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

        //遍历路径与模板名的映射关系， 使用通用模板替换器， 生成文件
        filePathAndTemplateNameMap.forEach((filePath, templateName) -> {
            CommonReplaceProcess commonReplaceProcess = new CommonReplaceProcess();
            commonReplaceProcess.setTargetPath(filePath);
            commonReplaceProcess.setTemplateName(templateName);
            commonReplaceProcess.setParam(buildTemplateInfo);
            commonReplaceProcess.templateReplace();
        });

        //处理资源文件。
        Map<String, String> resourceMap = new HashMap<>();
        List<String> resourceFilePaths = absoluteFilePaths.stream().filter(path -> !path.endsWith(GeneratorConstant.JAVA_SUFFIX)).toList();
        for (String resourceFilePath : resourceFilePaths) {
            String fileName = resourceFilePath.substring(resourceFilePath.lastIndexOf(File.separator) + 1);
            String realResourcePath = resourceFilePath.replaceAll(GeneratorConstant.COMPONENT_NAME_REPLACEMENT, moduleInfo.getComponentName());
            resourceMap.put(realResourcePath, "template/comp/" + fileName);
        }

        //创建目标资源文件。
        //读取文本内容
        //模板替换
        //写入目标文件
        resourceMap.forEach((targetResourcePath, srcResourcePath) -> {
            String targetResourceDir = targetResourcePath.substring(0, targetResourcePath.lastIndexOf(File.separator));
            FileUtil.mkdir(targetResourceDir);

            String targetFileName = targetResourcePath.substring(targetResourcePath.lastIndexOf(File.separator) + 1);
            File targetFile = FileUtil.file(targetResourceDir, targetFileName);

            try(InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(srcResourcePath);
                Writer writer = new BufferedWriter(new FileWriter(targetFile, Charset.forName("UTF-8")));) {
                String content = IoUtil.read(resourceAsStream, Charset.forName("UTF-8"));
                content = content.replaceAll(GeneratorConstant.COMPONENT_NAME_REPLACEMENT, buildTemplateInfo.getComponentName())
                        .replaceAll(GeneratorConstant.KEBAB_CASE_COMPONENT_NAME_REPLACEMENT, buildTemplateInfo.getKebabCaseComponentName())
                                .replaceAll(GeneratorConstant.COMPONENT_CNN_NAME_REPLACEMENT,buildTemplateInfo.getComponentCnName());
                writer.write(content);
            }catch (Exception exception) {
                GeneratorUtil.showErrorMessage(exception.getMessage());
            }
        });

    }

    private String getFileName(String absoluteFilePath) {
        int startIndex = absoluteFilePath.lastIndexOf(File.separator);
        int endIndex = absoluteFilePath.lastIndexOf(".");
        return absoluteFilePath.substring(startIndex + 1, endIndex);
    }


}
