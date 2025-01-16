plugins {
    id 'org.springframework.boot'  apply false
    id '${convertId}'
    id 'cn.chinaclear.sz.tool.devlint' version '1.4.2-SNAPSHOT'

}

apply plugin: 'DevLint'

dependencies {
    implementation 'cn.chinaclear.sz.bpmframework.param:param-maintain-spring-boot-starter'
}

description = '${subProjectName}'