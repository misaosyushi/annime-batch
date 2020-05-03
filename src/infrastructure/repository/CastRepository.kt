package com.annime.batch.infrastructure.repository

import com.annime.batch.domain.annime.AnnictCast
import com.annime.batch.infrastructure.database.Cast
import com.annime.batch.infrastructure.database.CastDao
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class CastRepository {

    fun insert(cast: AnnictCast, savedAnnictId: Long) {
        transaction {
            Cast.new {
                annictId = savedAnnictId
                name = cast.name
                characterName = cast.character.name
            }
        }
    }

    fun findByAnnictId(annictId: Long, characterName: String): List<Cast> {
        return transaction {
            Cast.find { (CastDao.annictId eq annictId) and (CastDao.characterName eq characterName) }.toList()
        }
    }

    fun update(cast: AnnictCast, annictId: Long, characterName: String) {
        val result = transaction {
            Cast.find { (CastDao.annictId eq annictId) and (CastDao.characterName eq characterName) }.single()
        }
        transaction {
            result.name = cast.name
            result.characterName = cast.character.name
        }
    }
}
