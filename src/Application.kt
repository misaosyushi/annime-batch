package com.annime.batch

import com.annime.batch.controller.sampleController
import com.annime.batch.domain.annime.Work
import com.annime.batch.usecase.SeasonServiceImpl
import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.Database
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.*

suspend fun main(args: Array<String>) {
    Database.connect("jdbc:mysql://0.0.0.0:13306/annime?useSSL=false", "com.mysql.jdbc.Driver", "annime", "annime")

    val client = HttpClient(Apache)

    val response = client.get<String>(
        scheme = "https",
        host = "api.annict.com",
        port = 443,
        path = "/v1/works?access_token=hmQkhdC3P--93g5pRo6ukW7_B84Y8sqgEhWixhxts0c&filter_season=2020-spring"
    )

    insertData(Gson().fromJson(response, Work::class.java))

    client.close()

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

fun insertData(annimes: Work) {

    val seasonService = SeasonServiceImpl()
    annimes.works.map { seasonService.insert(it.seasonNameText) }
}

