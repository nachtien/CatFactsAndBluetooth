package org.achtien.catfacts.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import models.ApiResponse
import models.CatFactsResponse
import org.achtien.catfacts.data.CatFacts

fun Route.feedRoutes() {
    route("/catfacts") {
        get {
            val lastId = call.queryParameters["lastId"]?.toLongOrNull() ?: 0L
            val limit = (call.queryParameters["limit"]?.toIntOrNull() ?: 20).coerceIn(1, 100)

            val facts = CatFacts.facts.filter { it.id > lastId }.take(limit)

            call.respond(
                HttpStatusCode.OK,
                ApiResponse(
                    success = true,
                    data = CatFactsResponse(
                        facts = facts,
                        limit = limit,
                        totalFacts = CatFacts.facts.size
                    )
                )
            )
        }
    }
}
