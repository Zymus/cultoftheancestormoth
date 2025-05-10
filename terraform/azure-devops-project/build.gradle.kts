plugins {
    alias(libs.plugins.terraform)
}

terraform {
    version = "1.11.4"

    sourceSets {
        main {
            // conventional tf apply and tf destroy guards
//            executeApplyOnlyIf { System.getenv("ENABLE_TF_APPLY") == "true" }
            executeApplyOnlyIf { true }
            executeDestroyOnlyIf { System.getenv("ENABLE_TF_DESTROY") == "true" }
        }
    }
}
