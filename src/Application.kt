package com.annime.batch

import com.annime.batch.controller.sampleController
import com.annime.batch.domain.annime.Episodes
import com.annime.batch.domain.annime.Work
import com.annime.batch.usecase.AnnimeServiceImpl
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

    val schemeName = "https"
    val hostName = "api.annict.com"
    val portNumber = 443
    val accessToken = System.getenv("ACCESS_TOKEN")
    val season = System.getenv("SEASON")

    val works = client.get<String>(
        scheme = schemeName,
        host = hostName,
        port = portNumber,
        path = "/v1/works?access_token=$accessToken&filter_season=$season&sort_watchers_count=desc"
    )

    val parsedWorks = Gson().fromJson(works, Work::class.java)
//    parsedWorks.works.map {
//        val workId = it.id
//        val episodes = client.get<String>(
//            scheme = schemeName,
//            host = hostName,
//            port = portNumber,
//            path = "/v1/episodes?access_token=$accessToken&filter_work_id=$workId&sort_sort_number=asc"
//        )
//        insertEpisode(Gson().fromJson(episodes, Episodes::class.java), workId)
//    }

    insertData(parsedWorks, season)

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
    val annimeService = AnnimeServiceImpl()

    val seasonId = seasonService.updateOrInsert(seasonService.findBySeasonText(season), season).id

    annimeService.updateOrInsert(annimes.works, seasonId.value)

    // TODO: episodesとcasts
}

fun insertEpisode(episodes: Episodes, workId: Long) {
    println(episodes)
}

