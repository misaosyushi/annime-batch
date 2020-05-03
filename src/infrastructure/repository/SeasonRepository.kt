package com.annime.batch.infrastructure.repository

import com.annime.batch.infrastructure.database.Season
import com.annime.batch.infrastructure.database.SeasonDao
import org.jetbrains.exposed.sql.transactions.transaction

class SeasonRepository {
    fun findAll(): List<Season> {
        return transaction {
            Season.all().toList()
        }
    }

    fun insert(season: String): Season {
        return transaction {
            Season.new {
                seasonText = season
            }
        }
    }

    fun findBySeasonText(season: String): List<Season> {
        return transaction {
            Season.find { SeasonDao.seasonText eq season }.toList()
        }
    }

    fun update(season: String): Season {
        val result = transaction {
            Season.find { SeasonDao.seasonText eq season }.single()
        }
        transaction {
            result.seasonText = season
        }
        return result
    }
}
