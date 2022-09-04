plugins {
    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("java")
    id("com.diffplug.spotless") version "6.4.2"
    id("org.openapi.generator") version "5.4.0"

}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}
apply(plugin = "org.openapi.generator")
group = "com.expenses"
version = "1.0-SNAPSHOT"
configurations {
    all {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
        exclude("ch.qos.logback", "logback-classic")
        exclude("org.apache.logging.log4j","log4j-to-slf4j")
    }
}
repositories {
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
    mavenCentral()
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql")
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("io.springfox:springfox-swagger2:2.6.1")
    implementation("io.springfox:springfox-swagger-ui:2.6.1")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
spotless {
    java {
        removeUnusedImports()
        googleJavaFormat("1.9")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
