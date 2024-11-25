package com.chinaclear.sz.component.generator;

import com.chinaclear.sz.component.pojo.ModuleInfo;
import com.chinaclear.sz.component.util.GeneratorUtil;

import java.util.List;

public class DirectoryGenerator {
    private ModuleInfo moduleInfo;

    public void generate() {
        List<Generator> generators = GeneratorFactory.getByType(moduleInfo.getType());

        for (Generator generator : generators) {
            generator.generate(moduleInfo);
        }
    }

    public ModuleInfo getModuleInfo() {
        return moduleInfo;
    }

    public void setModuleInfo(ModuleInfo moduleInfo) {
        this.moduleInfo = moduleInfo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String subProjectName;
        private String name;
        private String type;

        private String baseDir;
        private String version;
        private String convertId;
        private String packageName;
        private String authorName;

        private String componentName;

        private String componentCnName;
        public Builder subProjectName(String subProjectName) {
            this.subProjectName = subProjectName;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder baseDir(String baseDir) {
            this.baseDir = baseDir;
            return this;
        }
        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder convertId(String convertId) {
            this.convertId = convertId;
            return this;
        }

        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }
        public Builder authorName(String authorName) {
            this.authorName = authorName;
            return this;
        }
        public Builder componentName(String componentName) {
            this.componentName = componentName;
            return this;
        }
        public Builder componentCnName(String componentCnName) {
            this.componentCnName = componentCnName;
            return this;
        }
        public DirectoryGenerator build() {
            if (subProjectName == null) {
                GeneratorUtil.showErrorMessage("子项目不能为空");
                throw new RuntimeException("子项目不能为空");
            }

            if (type == null) {
                GeneratorUtil.showErrorMessage("应用类型不能为空");
                throw new RuntimeException("应用类型不能为空");
            }

            ModuleInfo moduleInfo = new ModuleInfo();
            moduleInfo.setSubProjectName(subProjectName);
            moduleInfo.setType(type);
            moduleInfo.setPackageName(packageName);
            moduleInfo.setBaseDir(baseDir);
            moduleInfo.setVersion(version);
            moduleInfo.setConvertId(convertId);
            moduleInfo.setAuthorName(authorName);
            moduleInfo.setComponentName(componentName);
            moduleInfo.setComponentCnName(componentCnName);
            DirectoryGenerator directoryGenerator = new DirectoryGenerator();
            directoryGenerator.setModuleInfo(moduleInfo);

            return directoryGenerator;
        }
    }
}
