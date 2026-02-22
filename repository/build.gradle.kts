plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.achtien.codingtemplate.repository"
    compileSdk = libs.versions.compileSdk.get().toInt()

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
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.coroutines)
    implementation(libs.koin.core)
    api(projects.networking)
}
