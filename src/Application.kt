package com.annime.batch

import com.annime.batch.controller.sampleController
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>) {
    Database.connect("jdbc:mysql://0.0.0.0:13306/annime", "com.mysql.jdbc.Driver", "annime", "annime")
    embeddedServer(
        Netty, watchPaths = listOf("build/classes"), port = 8081,
        module = Application::mymodule
    ).start(true)
}

fun Application.mymodule() {
    install(ContentNegotiation) {
        gson()
    }
    routing {
        sampleController()
    }
}

