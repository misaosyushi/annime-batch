package com.annime.batch.domain.annime

import com.google.gson.annotations.SerializedName

data class Episodes(
    val episodes: List<AnnictEpisode>
)

data class AnnictEpisode(
    @SerializedName("number_text")
    val numberText: String,
    val title: String
)
