package com.annime.batch.usecase

import com.annime.batch.domain.annime.Annict
import com.annime.batch.infrastructure.database.Annime
import com.annime.batch.infrastructure.repository.AnnimeRepository

interface AnnimeService {

    fun insertAll(annicts: List<Annict>, savedSeasonId: Int)

    fun findByAnnictId(annictId: Long): List<Annime>

    fun updateOrInsert(annicts: List<Annict>, savedSeasonId: Int)

}

class AnnimeServiceImpl : AnnimeService {

    private val repository = AnnimeRepository()

    override fun insertAll(annicts: List<Annict>, savedSeasonId: Int) {
        annicts.map { repository.insert(it, savedSeasonId) }
    }

    override fun findByAnnictId(annictId: Long): List<Annime> = repository.findByAnnictId(annictId)

    override fun updateOrInsert(annicts: List<Annict>, savedSeasonId: Int) {
        annicts.map {
            val annime = repository.findByAnnictId(it.id)
            if (annime.isEmpty()) {
                repository.insert(it, savedSeasonId)
            } else {
                repository.update(it, annime[0].annictId)
            }
        }
    }

}
