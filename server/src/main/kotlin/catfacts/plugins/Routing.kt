package org.achtien.catfacts.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.achtien.catfacts.routes.*

fun Application.configureRouting() {
    routing {
        feedRoutes()
    }
}
