plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
            testTask {
                useKarma {
                    useFirefox()
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        jsMain.dependencies {
            implementation(libs.kotlin.wrappers.emotion)
            implementation(libs.kotlin.wrappers.react)
            implementation(libs.kotlin.wrappers.react.dom)
            implementation(libs.kotlinx.io.core)
            implementation(libs.kotlinx.serialization.core)
            implementation(project(":bytestring-serializer"))
            implementation(project(":serialization"))
        }
    }
}
