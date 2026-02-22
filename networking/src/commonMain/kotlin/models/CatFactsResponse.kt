package models

import kotlinx.serialization.Serializable

@Serializable
data class CatFactsResponse(
    val facts: List<CatFact>,
    val limit: Int,
    val totalFacts: Int
)
