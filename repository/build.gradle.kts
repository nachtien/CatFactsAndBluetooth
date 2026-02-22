plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.sqlDelight)
}

android {
    namespace = "com.achtien.codingtemplate.repository"
    compileSdk = libs.versions.compileSdk.get().toInt()
    enableKotlin = false

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    lint {
        targetSdk = libs.versions.targetSdk.get().toInt()
    }
    testOptions {
        targetSdk = libs.versions.targetSdk.get().toInt()
    }
}

kotlin {
    jvmToolchain(17)
    androidTarget()
    iosArm64()
    iosSimulatorArm64()
    jvm()

    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.sqldelight.android.driver)
        }
        commonMain.dependencies {
            implementation(libs.sqldelight.coroutines.extensions)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            api(libs.koin.core)
            implementation(projects.networking)
            implementation(libs.sqldelight.runtime)
        }
        nativeMain.dependencies {
            implementation(libs.sqldelight.native.driver)
            implementation(libs.ktor.client.darwin)
        }
    }
}

sqldelight {
    databases {
        create("CatFactsDatabase") {
            generateAsync = true
            packageName.set("com.achtien.catfacts")
        }
    }
}
