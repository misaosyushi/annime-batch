package com.annime.batch.infrastructure.database

import org.jetbrains.exposed.dao.*

object EpisodeDao : LongIdTable("episodes") {
    val annictId = long("annict_id")
    val numberText = varchar("number_text", 10)
    val title = varchar("title", 100)
}

class Episode(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Episode>(
        EpisodeDao
    )

    var annictId by EpisodeDao.annictId
    var numberText by EpisodeDao.numberText
    var title by EpisodeDao.title
}
