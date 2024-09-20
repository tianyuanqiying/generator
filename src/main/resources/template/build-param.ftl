plugins {
    id 'org.springframework.boot'  apply false
    id 'cn.chinaclear.sz.mixed.app.settlementpath.java-conventions'
    id 'cn.chinaclear.sz.tool.devlint' version '1.4.2-SNAPSHOT'
}

apply plugin: 'DevLint'

dependencies {
    implementation 'cn.chinaclear.sz.bpmframework.param:param-maintain-spring-boot-starter:${version}'
    implementation 'cn.chinaclear.sz.bpm.business.param:business-param-common:1.0.1-SNAPSHOT'
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
}

description = '${menuId}'