import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.diffplug.spotless") version "6.25.0"
}

group = "lab.home"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

val buildDir = "${layout.buildDirectory.asFile.get()}"

val kotestVer = "5.9.1"
val kotestSpringExtVer = "1.3.0"
val kotestMockServerExtVer = "1.3.0"
val kotestTcExtVer = "2.0.2"

val mockkVer = "1.13.13"
val tcVer = "1.20.3"
val ktlintVer = "1.4.1"
val konsistVer = "0.16.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
//    implementation("org.springframework.boot:spring-boot-starter-security")
//    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    // mysql connectors
    runtimeOnly("com.mysql:mysql-connector-j") // JDBC
    runtimeOnly("io.asyncer:r2dbc-mysql") // R2DBC

    // kotest and mockk
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVer")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVer")
    testImplementation("io.kotest:kotest-property:$kotestVer")
    testImplementation("io.kotest:kotest-framework-datatest:$kotestVer")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:$kotestSpringExtVer")
    testImplementation("io.kotest.extensions:kotest-extensions-mockserver:$kotestMockServerExtVer")
    testImplementation("io.mockk:mockk:$mockkVer")

    // Testcontainers
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:testcontainers:$tcVer")
    testImplementation(platform("org.testcontainers:testcontainers-bom:$tcVer"))
    testImplementation("org.testcontainers:mysql")
    testImplementation("org.testcontainers:r2dbc")

    // Arch Linter : Konsist
    testImplementation("com.lemonappdev:konsist:$konsistVer")

    // Dev tools
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events = setOf(FAILED, PASSED, SKIPPED)
    }
}

configure<SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        targetExclude("$buildDir/**/*.kt")
        ktlint(ktlintVer).editorConfigOverride(
            mapOf(
                "ktlint_code_style" to "ktlint_official",
                "ktlint_standard_package-name" to "disabled"
            )
        )
    }
    kotlinGradle {
        target("**/*.gradle.kts")
        targetExclude("$buildDir/**/*.kt")
        ktlint(ktlintVer).editorConfigOverride(
            mapOf(
                "ktlint_code_style" to "ktlint_official"
            )
        )
    }
    java {
        target("**/*.java")
        removeUnusedImports()
    }
}

afterEvaluate {
    kotlin.runCatching {
        tasks.getByPath("classes").dependsOn(tasks.spotlessApply)
    }
    kotlin.runCatching {
        tasks.getByPath("preBuild").dependsOn(tasks.spotlessApply)
    }
}
