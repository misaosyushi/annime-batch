package com.annime.batch.infrastructure.repository

import com.annime.batch.infrastructure.database.Season
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
}
