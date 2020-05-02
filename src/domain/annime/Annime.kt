package com.annime.batch.domain.annime

import com.google.gson.annotations.SerializedName

data class Work(
    val works: List<Annime>
)

data class Annime(
    val id: Long,
    val title: String,
    @SerializedName("media_text")
    val mediaText: String,
    @SerializedName("season_name_text")
    val seasonNameText: String,
    @SerializedName("official_site_url")
    val officialSiteUrl: String,
    @SerializedName("twitter_username")
    val twitterUserName: String,
    val images: Image
)

data class Image(
    @SerializedName("recommended_url")
    val recommendedUrl: String
)
