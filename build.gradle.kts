import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "1.9.22"
    id("org.jetbrains.compose") version "1.6.0"
    id("org.springframework.boot") version "2.6.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "com.xtl"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation("org.jetbrains.compose.material3:material3:1.6.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.7")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.5")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.6.5")
    implementation("com.h2database:h2:2.1.214")
    implementation("org.projectlombok:lombok:1.18.32")
    implementation("cn.hutool:hutool-all:5.8.25")
    implementation("org.apache.commons:commons-lang3:3.14.0")

    // SLF4J 和 Logback 依赖
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("ch.qos.logback:logback-classic:1.2.11")

    // MyBatis-Plus 依赖
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.2")
    implementation("com.baomidou:mybatis-plus-annotation:3.5.2")

    implementation("io.appium:java-client:9.2.2")
    implementation("org.seleniumhq.selenium:selenium-java:4.20.0")
    implementation("org.seleniumhq.selenium:selenium-api:4.20.0")
    implementation("org.seleniumhq.selenium:selenium-remote-driver:4.20.0")

    // 测试
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("com.h2database:h2:2.1.214")

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")

    annotationProcessor("org.projectlombok:lombok:1.18.32")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = "17"
    targetCompatibility = "17"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "e-commerce"
            packageVersion = "1.0.0"
        }
    }
}

// Spring Boot 主类配置
tasks.register<JavaExec>("runSpringBoot") {
    group = "application"
    description = "Run the Spring Boot application"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.xtl.automation.AutomationApplication")
}

springBoot {
    mainClass.set("com.xtl.automation.AutomationApplication")
}
