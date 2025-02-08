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
        browser {
            testTask {
                useKarma {
                    useChromiumHeadless()
                    //useFirefoxHeadless()// doesn't seem to work on ubuntu
                }
            }
        }
        binaries.executable()
    }
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.arrow.core)
            implementation(libs.kotlinx.io.core)
            implementation(libs.kotlinx.serialization.core)
            implementation(project(":bytestring-serializer"))
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
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
