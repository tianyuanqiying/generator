package com.chinaclear.sz.component.generator;

/**
 * 模板参数信息
 */
public class BuildTemplateParam {
    private String menuId;

    private String version;

    private String menuType;

    private String baseRootDir;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
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
}
