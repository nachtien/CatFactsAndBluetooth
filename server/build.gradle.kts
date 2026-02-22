plugins {
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlinJvm)
    application
}

group = "org.achtien.brightai"
version = "1.0.0"
application {
    mainClass.set("org.achtien.catfacts.ApplicationKt")

//    val isDevelopment: Boolean = project.ext.has("development")
    val isDevelopment: Boolean = true
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.networking)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.serialization)
    implementation(libs.ktor.client.serialization)

    implementation("io.ktor:ktor-server-core:3.4.0")
    implementation("io.ktor:ktor-server-netty:3.4.0")
    implementation("io.ktor:ktor-server-cors:3.4.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.4.0")
    implementation("io.ktor:ktor-server-content-negotiation:3.4.0")
    implementation("io.ktor:ktor-server-auth:3.4.0")
    implementation("io.ktor:ktor-server-auth-jwt:3.4.0")
    implementation("io.ktor:ktor-server-status-pages:3.4.0")
    implementation("io.ktor:ktor-client-cio:3.4.0")
    implementation("io.ktor:ktor-client-content-negotiation:3.4.0")

    implementation("at.favre.lib:bcrypt:0.10.2")

    implementation("ch.qos.logback:logback-classic:1.5.25")
}
