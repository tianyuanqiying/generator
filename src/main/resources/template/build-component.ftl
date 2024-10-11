group = 'cn.chinaclear.sz.component'
version = '1.0.1-SNAPSHOT'
description = '${subProjectName}'

ext {
    artifactoryBaseUrl = 'http://maven.sz.chinaclear.cn/content/repositories'
    artifactorySnapshotRepoUrl = "$artifactoryBaseUrl/snapshots"
    artifactoryReleaseRepoUrl = "$artifactoryBaseUrl/releases"
}

publishing {
    publications {
        maven(MavenPublication) {
            from(components.java)
        }
    }

    repositories {
        maven {
            name 'remoteArtifactory'
            url version.endsWith('SNAPSHOT') ? artifactorySnapshotRepoUrl : artifactoryReleaseRepoUrl
            allowInsecureProtocol = true
            credentials {
                username(System.getenv("MAVEN_PUBLISH_USERNAME"))
                password(System.getenv("MAVEN_PUBLISH_PASSWORD"))
            }
        }
    }
}
