package com.annime.batch

import io.ktor.application.*
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, 8081) {
        routing {
            get("/") {
                call.respond(HttpStatusCode.OK,"Hello, Kotlin")
            }
        }
    }
    server.start()
}

