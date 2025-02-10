package com.chinaclear.sz.component.anno;

/**
 * 导入Bean的配置文件
 */
public @interface ImportResource {
    String[] value() default {};

}
