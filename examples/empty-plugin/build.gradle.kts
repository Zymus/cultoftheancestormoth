plugins {
    alias(libs.plugins.kotlinMultiplatform)
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
    }
}
