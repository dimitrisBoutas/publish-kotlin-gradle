import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
    id("maven-publish")
}

group = "dimi.boo"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/dimitrisBoutas/publish-kotlin-gradle")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("dimiBoo") {
            groupId = "dimi"
            artifactId = "boo"
            version = "1.0.0"
            from(components["java"])
        }
    }

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
