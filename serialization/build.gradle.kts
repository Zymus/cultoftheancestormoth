plugins {
    alias(libs.plugins.kotlinMultiplatform)
    kotlin("plugin.serialization") version "1.9.23"
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
}