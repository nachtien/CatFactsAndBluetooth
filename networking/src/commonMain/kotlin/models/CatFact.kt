package models

import kotlinx.serialization.Serializable

@Serializable
data class CatFact(
    val id: Long,
    val fact: String
)
