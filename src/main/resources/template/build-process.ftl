plugins {
    id 'org.springframework.boot'  apply false
    id '${convertId}'
    id 'cn.chinaclear.sz.tool.devlint' version '1.4.2-SNAPSHOT'
}

apply plugin: 'DevLint'

dependencies {
    implementation 'cn.chinaclear.sz.bpmframework:bpm-process-spring-boot-starter'
}

DevLint {
   workflow {
     PbuNewOrOpen {
      workflowDefinitionFile = file("./src/main/resources/workflow/PbuNewOrOpen.workflow")
     }
  }
}

description = '${subProjectName}'