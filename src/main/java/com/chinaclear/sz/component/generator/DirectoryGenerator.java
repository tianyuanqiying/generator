package com.chinaclear.sz.component.generator;

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
        private String id;
        private String name;
        private String type;

        private String baseDir;
        private String version;

        public DirectoryGenerator.Builder id(String id) {
            this.id = id;
            return this;
        }

        public DirectoryGenerator.Builder type(String type) {
            this.type = type;
            return this;
        }

        public DirectoryGenerator.Builder baseDir(String baseDir) {
            this.baseDir = baseDir;
            return this;
        }
        public DirectoryGenerator.Builder version(String version) {
            this.version = version;
            return this;
        }

        public DirectoryGenerator build() {
            if (id == null) {
                throw new RuntimeException("id 不能为空");
            }

            if (type == null) {
                throw new RuntimeException("应用类型不能为空");
            }

            ModuleInfo moduleInfo = new ModuleInfo();
            moduleInfo.setId(id);
            moduleInfo.setType(type);
            moduleInfo.setName(id);
            moduleInfo.setBaseDir(baseDir);
            moduleInfo.setVersion(version);
            DirectoryGenerator directoryGenerator = new DirectoryGenerator();
            directoryGenerator.setModuleInfo(moduleInfo);

            return directoryGenerator;
        }
    }
}
