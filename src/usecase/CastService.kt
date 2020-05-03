package com.annime.batch.usecase

import com.annime.batch.domain.annime.AnnictCast
import com.annime.batch.infrastructure.repository.CastRepository

interface CastService {

    fun updateOrInsert(casts: List<AnnictCast>, savedAnnictId: Long)
}

class CastServiceImple() : CastService {

    private val repository = CastRepository()

    override fun updateOrInsert(casts: List<AnnictCast>, savedAnnictId: Long) {
        casts.map {
            val cast = repository.findByAnnictId(savedAnnictId, it.character.name)
            if (cast.isEmpty()) {
                repository.insert(it, savedAnnictId)
            } else {
                repository.update(it, cast[0].annictId, it.character.name)
            }
        }
    }

}
