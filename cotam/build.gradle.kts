plugins {
    //alias(libs.plugins.kotlinMultiplatform)
    application
    kotlin("jvm") version "2.0.10"
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

/*kotlin {
    jvm()

    sourceSets {
        jvmMain.dependencies {
            implementation("com.github.ajalt.clikt:clikt:5.0.1")
        }
    }
}*/

dependencies {
    implementation("com.github.ajalt.clikt:clikt:5.0.1")
}

application {
    mainClass = "games.studiohummingbird.cultoftheancestormoth.CotamKt"
}
