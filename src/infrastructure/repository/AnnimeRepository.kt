package com.annime.batch.infrastructure.repository

import com.annime.batch.domain.annime.Annict
import com.annime.batch.infrastructure.database.Annime
import com.annime.batch.infrastructure.database.AnnimeDao
import org.jetbrains.exposed.sql.transactions.transaction

class AnnimeRepository {

    fun findAll(): List<Annime> {
        return transaction {
            Annime.all().toList()
        }
    }

    fun insert(annict: Annict, savedSeasonId: Int) {
        transaction {
            Annime.new {
                annictId = annict.id
                title = annict.title
                media = annict.mediaText
                seasonId = savedSeasonId
                officialSiteUrl = annict.officialSiteUrl
                twitterUserName = annict.twitterUserName
                imageUrl = annict.images.recommendedUrl
            }
        }
    }

    fun findByAnnictId(annictId: Long): List<Annime> {
        return transaction {
            Annime.find { AnnimeDao.annictId eq annictId }.toList()
        }
    }

    fun update(annict: Annict, annictId: Long): Annime {
        val result = transaction {
            Annime.find { AnnimeDao.annictId eq annictId }.single()
        }
        transaction {
            result.title = annict.title
            result.media = annict.mediaText
            result.officialSiteUrl = annict.officialSiteUrl
            result.twitterUserName = annict.twitterUserName
            result.imageUrl = annict.images.recommendedUrl
        }
        return result
    }
}
