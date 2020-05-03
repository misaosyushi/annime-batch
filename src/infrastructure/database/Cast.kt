package com.annime.batch.infrastructure.database

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.LongIdTable

object CastDao : LongIdTable("casts") {
    val annictId = long("annict_id")
    val name = varchar("name", 40)
    val characterName = varchar("character_name", 40)
}

class Cast(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Cast>(
        CastDao
    )

    var annictId by CastDao.annictId
    var name by CastDao.name
    var characterName by CastDao.characterName
}
