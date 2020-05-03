package com.annime.batch

import com.annime.batch.infrastructure.httpclient.AnnictClient
import io.ktor.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database

suspend fun main(args: Array<String>) {
    Database.connect("jdbc:mysql://0.0.0.0:13306/annime?useSSL=false", "com.mysql.jdbc.Driver", "annime", "annime")

    val client = AnnictClient()
    client.requestAnnictAPI()

    embeddedServer(
        Netty, watchPaths = listOf("build/classes"), port = 8081,
        module = Application::mymodule
    ).start(true)
}

fun Application.mymodule() {
}

