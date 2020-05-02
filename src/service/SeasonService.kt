package com.annime.batch.service

import com.annime.batch.infrastructure.Season
import org.jetbrains.exposed.sql.transactions.transaction

class SeasonService {

    fun findAll(): List<Season> {
        var result: List<Season> = listOf()
        transaction {
            result = Season.all().toList()
        }
        return result
    }
}
