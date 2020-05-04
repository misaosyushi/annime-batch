package com.annime.batch.infrastructure.httpclient

import com.annime.batch.domain.annime.Casts
import com.annime.batch.domain.annime.Episodes
import com.annime.batch.domain.annime.Work
import com.annime.batch.usecase.AnnimeServiceImpl
import com.annime.batch.usecase.CastServiceImple
import com.annime.batch.usecase.EpisodeServiceImpl
import com.annime.batch.usecase.SeasonServiceImpl
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get

class AnnictClient {

    suspend fun requestAnnictAPI() {
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
        saveAnnimes(parsedWorks, season)

        parsedWorks.works.map {
            val workId = it.id
            val episodes = client.get<String>(
                scheme = schemeName,
                host = hostName,
                port = portNumber,
                path = "/v1/episodes?access_token=$accessToken&filter_work_id=$workId&sort_sort_number=asc"
            )
            saveEpisodes(Gson().fromJson(episodes, Episodes::class.java), workId)
            // NOTE: 429エラー回避のため3秒待機させてる
            Thread.sleep(3000)
        }

        parsedWorks.works.map {
            val workId = it.id
            val episodes = client.get<String>(
                scheme = schemeName,
                host = hostName,
                port = portNumber,
                path = "/v1/casts?access_token=$accessToken&filter_work_id=$workId&sort_sort_number=asc"
            )
            saveCasts(Gson().fromJson(episodes, Casts::class.java), workId)
            // NOTE: 429エラー回避のため3秒待機させてる
            Thread.sleep(3000)
        }

        client.close()
    }

    private fun saveAnnimes(annimes: Work, season: String) {
        val seasonService = SeasonServiceImpl()
        val annimeService = AnnimeServiceImpl()

        val seasonId = seasonService.updateOrInsert(seasonService.findBySeasonText(season), season).id

        annimeService.updateOrInsert(annimes.works, seasonId.value)
    }

    private fun saveEpisodes(episodes: Episodes, workId: Long) {
        val episodeService = EpisodeServiceImpl()
        episodeService.updateOrInsert(episodes.episodes, workId)
    }

    private fun saveCasts(casts: Casts, workId: Long) {
        val castService = CastServiceImple()
        castService.updateOrInsert(casts.casts, workId)
    }
}
