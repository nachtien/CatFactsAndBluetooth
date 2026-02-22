package api

import getPlatform
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import models.ApiResponse
import models.CatFactsResponse


private fun defaultBaseUrl(): String {
    val platform = getPlatform().name
    return when {
        platform.startsWith("Android") -> "http://10.0.2.2:8080"
        else -> "http://localhost:8080"
    }
}

class Api(private val client: HttpClient, private val baseUrl: String = defaultBaseUrl()) {
    suspend fun getCatFacts(lastId: Long, limit: Int = 20): ApiResponse<CatFactsResponse> {
        return client.get("$baseUrl/catfacts?lastId=$lastId&limit=$limit").body()
    }
}
