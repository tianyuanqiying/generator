package com.chinaclear.sz.component.pojo;

/**
 * 模板参数信息
 */
public class BuildTemplateParam implements Param {
    private String subProjectName;

    private String version;

    private String menuType;

    private String baseRootDir;

    private String convertId;

    private String authorName;

    private String createDate;

    private String packageName;

    private String componentName;

    private String componentCnName;

    private String kebabCaseComponentName;

    public String getComponentCnName() {
        return componentCnName;
    }

    public void setComponentCnName(String componentCnName) {
        this.componentCnName = componentCnName;
    }

    public String getKebabCaseComponentName() {
        return kebabCaseComponentName;
    }

    public void setKebabCaseComponentName(String kebabCaseComponentName) {
        this.kebabCaseComponentName = kebabCaseComponentName;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getBaseRootDir() {
        return baseRootDir;
    }

    public void setBaseRootDir(String baseRootDir) {
        this.baseRootDir = baseRootDir;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
