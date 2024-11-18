plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

kotlin {
    js {
        browser()
        binaries.executable()
    }
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.arrow.core)
            implementation(libs.kotlinx.io.core)
            implementation(libs.kotlinx.serialization.core)
            implementation(project(":model"))
        }

        jvmMain.dependencies {
            implementation(libs.arrow.core)
            implementation(libs.kotlinx.serialization.core)
        }

        jsMain.dependencies {
            implementation(libs.arrow.core)
            implementation(libs.kotlin.wrappers.js)
            implementation(libs.kotlinx.serialization.core)
        }
    }
}
