plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    jvmToolchain(17)

    iosArm64()
    iosSimulatorArm64()
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.ktor.common)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.serialization)

            api(libs.koin.core)
            api(libs.kermit)
        }

        androidMain.dependencies {

        }

    }

    androidLibrary {
        namespace = "com.achtien.brightai.common"
        compileSdk = libs.versions.compileSdk.get().toInt()
    }

}

kotlin.sourceSets.all {
    languageSettings.optIn("com.juul.kable.ExperimentalApi")
    languageSettings.optIn("kotlin.uuid.ExperimentalUuidApi")
    languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}
