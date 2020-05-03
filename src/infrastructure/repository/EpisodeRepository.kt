package com.annime.batch.infrastructure.repository

import com.annime.batch.domain.annime.AnnictEpisode
import com.annime.batch.infrastructure.database.Episode
import com.annime.batch.infrastructure.database.EpisodeDao
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class EpisodeRepository {

    fun insert(episode: AnnictEpisode, savedAnnictId: Long) {
        transaction {
            Episode.new {
                annictId = savedAnnictId
                title = episode.title
                numberText = episode.numberText
            }
        }
    }

    fun findByAnnictId(annictId: Long, numberText: String): List<Episode> {
        return transaction {
            Episode.find { (EpisodeDao.annictId eq annictId) and (EpisodeDao.numberText eq numberText) }.toList()
        }
    }

    fun update(episode: AnnictEpisode, annictId: Long, numberText: String) {
        val result = transaction {
            Episode.find { (EpisodeDao.annictId eq annictId) and (EpisodeDao.numberText eq numberText) }.single()
        }
        transaction {
            result.title = episode.title
            result.numberText = episode.numberText
        }
    }
}
