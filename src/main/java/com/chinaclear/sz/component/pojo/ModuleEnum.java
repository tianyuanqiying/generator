package com.chinaclear.sz.component.pojo;

public enum ModuleEnum {
    PROCESS("流程", 1),
    PARAM("参维", 2),
    QUERY("查询", 3),
    COMPONENT("组件", 4);
    final String name;
    final Integer type;

    ModuleEnum(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public static ModuleEnum getType(String name) {
        for (ModuleEnum moduleEnum : ModuleEnum.values()) {
            if (moduleEnum.name.equals(name)) {
                return moduleEnum;
            }
        }
        return null;
    }


}
