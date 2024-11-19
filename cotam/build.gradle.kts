plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

kotlin {
    jvm()

    sourceSets {
        jvmMain.dependencies {
            implementation(libs.clikt)
            implementation(project(":model"))
            implementation(libs.kotlinx.serialization.core)
            implementation(project(":serialization"))
        }
    }
}
