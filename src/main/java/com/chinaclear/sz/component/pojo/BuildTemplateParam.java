package com.chinaclear.sz.component.pojo;

/**
 * 模板参数信息
 */
public class BuildTemplateParam {
    private String subProjectName;

    private String version;

    private String menuType;

    private String baseRootDir;

    private String convertId;

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
}
