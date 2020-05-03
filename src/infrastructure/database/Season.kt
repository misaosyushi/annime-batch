package com.annime.batch.infrastructure.database

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object SeasonDao : IntIdTable("season") {
    val seasonText = varchar("season_text", 20)
}

class Season(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Season>(
        SeasonDao
    )

    var seasonText by SeasonDao.seasonText
}
