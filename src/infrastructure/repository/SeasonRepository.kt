package com.annime.batch.infrastructure.repository

import com.annime.batch.infrastructure.database.Season
import com.annime.batch.infrastructure.database.SeasonDao
import org.jetbrains.exposed.sql.transactions.transaction

class SeasonRepository {
    fun findAll(): List<Season> {
        var result: List<Season> = listOf()
        transaction {
            result = Season.all().toList()
        }
        return result
    }

    fun insert(season: String) {
        transaction {
            Season.new {
                seasonText = season
            }
        }
    }

    fun findBySeasonText(season: String): List<Season> {
        val result = transaction {
            Season.find { SeasonDao.seasonText eq season }.toList()
        }
        return result
    }

//    fun update(season: String): Season {
//        val result = findBySeasonText(season)
//        result.seasonText = season
//        return result
//    }
}
