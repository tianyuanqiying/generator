package com.chinaclear.sz.component.generator;

public enum ModuleEnum {
    PROCESS("流程", 1),
    PARAM("参维", 2),
    QUERY("查询", 3),
    COMPONENT("组件", 4);
    final java.lang.String name;
    final Integer type;

    ModuleEnum(java.lang.String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public java.lang.String getName() {
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
