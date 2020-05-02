package com.annime.batch.infrastructure

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object SeasonDao : IntIdTable("season") {
    val year = integer("year")
    val season = varchar("season", 5)
    val seasonText = varchar("season_text", 10)
}

class Season(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Season>(SeasonDao)

    var year by SeasonDao.year
    var season by SeasonDao.season
    var seasonText by SeasonDao.seasonText
}
