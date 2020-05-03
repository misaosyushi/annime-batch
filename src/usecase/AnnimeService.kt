package com.annime.batch.usecase

import com.annime.batch.domain.annime.Annict
import com.annime.batch.infrastructure.repository.AnnimeRepository

interface AnnimeService {

    fun insertAll(annict: List<Annict>, savesSeasonId: Int)

}

class AnnimeServiceImpl : AnnimeService {

    private val repository = AnnimeRepository()

    override fun insertAll(annict: List<Annict>, savesSeasonId: Int) {
        annict.map { repository.insert(it, savesSeasonId) }
    }

}
