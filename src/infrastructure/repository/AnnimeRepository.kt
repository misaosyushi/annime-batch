package com.annime.batch.infrastructure.repository

import com.annime.batch.domain.annime.Annict
import com.annime.batch.infrastructure.database.Annime
import org.jetbrains.exposed.sql.transactions.transaction

class AnnimeRepository {

    fun findAll(): List<Annime> {
        return transaction {
            Annime.all().toList()
        }
    }

    fun insert(annict: Annict, savesSeasonId: Int) {
        transaction {
            Annime.new {
                annictId = annict.id
                title = annict.title
                media = annict.mediaText
                seasonId = savesSeasonId
                officialSiteUrl = annict.officialSiteUrl
                twitterUserName = annict.twitterUserName
                imageUrl = annict.images.recommendedUrl
            }
        }
    }
}
