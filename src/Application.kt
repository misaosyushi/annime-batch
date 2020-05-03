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
    val accessToken = System.getenv("ACCESS_TOKEN")
    val season = System.getenv("SEASON")

    val response = client.get<String>(
        scheme = "https",
        host = "api.annict.com",
        port = 443,
        path = "/v1/works?access_token=$accessToken&filter_season=$season"
    )

    insertData(Gson().fromJson(response, Work::class.java), season)

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

fun insertData(annimes: Work, season: String) {
    val seasonService = SeasonServiceImpl()
    println(annimes)
    val savedSeason = seasonService.findBySeasonText(season)
    println(savedSeason)
//    seasonService.insert(season)
    // TODO: 環境変数から2020-springをとってきて、seasonに保存。保存した戻り値でidを返せるが調査。
    // TODO: seasonに保存したidをseason_idにセットしてannnimesに保存
}

