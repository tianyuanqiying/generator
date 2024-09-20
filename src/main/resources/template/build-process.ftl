plugins {
    id 'org.springframework.boot'  apply false
    id 'cn.chinaclear.sz.mixed.app.settlementpath.java-conventions'
    id 'cn.chinaclear.sz.tool.devlint' version '1.4.2-SNAPSHOT'
}

apply plugin: 'DevLint'

dependencies {
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    implementation 'cn.chinaclear.sz.bpmframework:bpm-process-spring-boot-starter:${version}'
}

description = '${menuId}'