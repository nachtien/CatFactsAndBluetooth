package com.achtien.codingtemplate.di

import com.achtien.codingtemplate.api.Api
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun networkingModule(enableNetworkLogs: Boolean) = module {
    singleOf(::createJson)
    single<HttpClientEngine> { Android.create() }
    single { createHttpClient(get(), get(), enableNetworkLogs = enableNetworkLogs) }
    singleOf(::Api)
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

private fun createHttpClient(httpClientEngine: HttpClientEngine, json: Json, enableNetworkLogs: Boolean) = HttpClient(httpClientEngine) {
    install(ContentNegotiation) {
        json(json)
    }

    if (enableNetworkLogs) {
        install(Logging) {
            level = LogLevel.INFO
        }
    }
}
