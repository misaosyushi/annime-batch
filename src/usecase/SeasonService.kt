package com.annime.batch.usecase

import com.annime.batch.infrastructure.database.Season
import com.annime.batch.infrastructure.repository.SeasonRepository

interface SeasonService {

    fun findAll(): List<Season>

    fun insert(season: String)

    fun findBySeasonText(season: String): List<Season>

    fun updateOrInsert(savedList: List<Season>, season: String): Season
}

class SeasonServiceImpl() : SeasonService {

    private val repository = SeasonRepository()

    override fun findAll(): List<Season> = repository.findAll()

    override fun insert(season: String) {
        repository.insert(season)
    }

    override fun findBySeasonText(season: String): List<Season> = repository.findBySeasonText(season)

    override fun updateOrInsert(savedList: List<Season>, season: String): Season {
        if (savedList.isEmpty()) {
            return repository.insert(season)
        }
        return repository.update(season)
    }
}
