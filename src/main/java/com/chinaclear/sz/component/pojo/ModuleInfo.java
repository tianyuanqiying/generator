package com.chinaclear.sz.component.pojo;

public class ModuleInfo {
    private String subProjectName;
    private String packageName;
    private String type;

    private String version;

    private String baseDir;

    private String convertId;

    private String authorName;

    private String componentName;

    private String componentCnName;

    public String getComponentCnName() {
        return componentCnName;
    }

    public void setComponentCnName(String componentCnName) {
        this.componentCnName = componentCnName;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getConvertId() {
        return convertId;
    }

    public void setConvertId(String convertId) {
        this.convertId = convertId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
