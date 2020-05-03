package com.annime.batch.usecase

import com.annime.batch.domain.annime.AnnictEpisode
import com.annime.batch.infrastructure.repository.EpisodeRepository

interface EpisodeService {

    fun updateOrInsert(episodes: List<AnnictEpisode>, savedAnnictId: Long)
}

class EpisodeServiceImpl() : EpisodeService {

    private val repository = EpisodeRepository()

    override fun updateOrInsert(episodes: List<AnnictEpisode>, savedAnnictId: Long) {
        episodes.map {
            val episode = repository.findByAnnictId(savedAnnictId, it.numberText)
            if (episode.isEmpty()) {
                repository.insert(it, savedAnnictId)
            } else {
                repository.update(it, episode[0].annictId, it.numberText)
            }
        }
    }

}
