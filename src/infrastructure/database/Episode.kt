package com.annime.batch.infrastructure.database

import org.jetbrains.exposed.dao.*

object EpisodeDao : LongIdTable("episodes") {
    val annimeId = long("annime_id")
    val numberText = varchar("number_text", 10)
    val title = varchar("title", 100)
}

class Episode(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Episode>(
        EpisodeDao
    )

    var annimeId by EpisodeDao.annimeId
    var numberText by EpisodeDao.numberText
    var title by EpisodeDao.title
}
