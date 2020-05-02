package com.annime.batch.usecase

import com.annime.batch.infrastructure.database.Season
import com.annime.batch.infrastructure.repository.SeasonRepository

interface SeasonService {

    fun findAll(): List<Season>

    fun insert(season: String)
}

class SeasonServiceImpl() : SeasonService {

    private val repository = SeasonRepository()

    override fun findAll(): List<Season> {
        return repository.findAll()
    }

    override fun insert(season: String) {
        repository.insert(season)
    }
}
