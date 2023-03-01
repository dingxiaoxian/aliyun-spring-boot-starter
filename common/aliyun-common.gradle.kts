fun properties(key: String) = (project.findProperty(key) ?: rootProject.findProperty(key)).toString()
buildscript {
    repositories {
        mavenLocal()
        maven("https://maven.aliyun.com/repository/public")
        mavenCentral()
    }
}

plugins {
    id("java-library")
    id("idea")
    id("maven-publish")
    id("signing")
}

dependencies {
}

tasks {
    withType<JavaCompile> {
        doFirst {
            println("当前Java版本为：$sourceCompatibility")
        }
        options.encoding = "UTF-8"
    }
    test {
        useJUnitPlatform()
    }
    jar {
        enabled = true
        archiveClassifier.set("")
    }
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        withJavadocJar()
        withSourcesJar()
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            groupId = project.group as String
            artifactId = project.name
            version = project.version as String
            from(components["java"])
            pom {
                name.set(project.name)
                description.set(properties("projectDescription"))
                url.set(properties("projectUrl"))
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://www.mit.edu/~amini/LICENSE.md")
                    }
                }
                developers {
                    developer {
                        id.set(properties("projectAuthorId"))
                        name.set(properties("projectAuthorName"))
                        email.set(properties("projectAuthorEmail"))
                    }
                }
                scm {
                    connection.set(properties("projectScmUrl"))
                    developerConnection.set(properties("projectScmUrl"))
                    url.set(properties("projectUrl"))
                }
            }
        }
    }
}

signing {
    setRequired {
        !version.toString().endsWith("-SNAPSHOT") && gradle.taskGraph.hasTask("publishToSonatype")
    }
    useInMemoryPgpKeys(
        System.getenv("GPG_KEY_ID"),
        System.getenv("GPG_PRIVATE_KEY"),
        System.getenv("GPG_PRIVATE_KEY_PASSWORD")
    )
    sign(publishing.publications["mavenJava"])
}
