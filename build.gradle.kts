import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"

    //id ("org.springdoc.openapi-gradle-plugin") version "1.7.0"
}

group = "com.scimbosh"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    //sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    runtimeOnly("org.postgresql:postgresql")

    //security
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")

    //springdoc
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    testImplementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.2.0")

    //extend springdoc
    implementation("org.springdoc:springdoc-openapi-starter-common:2.2.0")
    //springdoc-security
    implementation("org.springdoc:springdoc-openapi-security:1.7.0")

    //health-check
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    //thymeleaf
//	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
//	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
        //jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
