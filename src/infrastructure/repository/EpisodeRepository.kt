package com.annime.batch.infrastructure.repository

import com.annime.batch.domain.annime.AnnictEpisode
import com.annime.batch.infrastructure.database.Episode
import com.annime.batch.infrastructure.database.EpisodeDao
import org.jetbrains.exposed.sql.transactions.transaction

class EpisodeRepository {

    fun insert(episode: AnnictEpisode, savedAnnimeId: Long) {
        transaction {
            Episode.new {
                annimeId = savedAnnimeId
                title = episode.title
                numberText = episode.numberText
            }
        }
    }

    fun findByAnnictId(annictId: Long): List<Episode> {
        return transaction {
            Episode.find { EpisodeDao.annimeId eq annictId }.toList()
        }
    }

    fun update(episode: AnnictEpisode, annictId: Long): Episode {
        val result = transaction {
            Episode.find { EpisodeDao.annimeId eq annictId }.single()
        }
        transaction {
            result.title = episode.title
            result.numberText = episode.numberText
        }
        return result
    }
}
