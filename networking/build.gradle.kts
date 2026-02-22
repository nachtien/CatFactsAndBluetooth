plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    id("kotlinx-serialization")
}

kotlin {
    jvmToolchain(17)
    iosArm64()
    iosSimulatorArm64()
    jvm()

    sourceSets {
        commonMain.dependencies {
            api(libs.koin.core)
            api(libs.kotlinx.coroutines)
            api(libs.ktor.client.core)
            api(libs.ktor.serialization.kotlinx.json)
            api(libs.ktor.client.content.negotiation)
            api(libs.ktor.client.logging)
            api(libs.kermit)
            implementation(projects.common)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.androidx.core.ktx)
            implementation(libs.ktor.client.android)
            implementation(libs.ktor.client.logging)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.java)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }

    androidLibrary {
        namespace = "com.achtien.brightai.networking"
        compileSdk = libs.versions.compileSdk.get().toInt()
    }
}

