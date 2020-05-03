package com.annime.batch.infrastructure.database

import org.jetbrains.exposed.dao.*

object AnnimeDao : LongIdTable("annimes") {
    val annictId = long("annict_id")
    val title = varchar("title", 100)
    val media = varchar("media", 10)
    val seasonId = integer("season_id")
    val officialSiteUrl = varchar("official_site_url", 400)
    val twitterUserName = varchar("twitter_user_name", 100)
    val imageUrl = varchar("image_url", 400)
}

class Annime(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Annime>(
        AnnimeDao
    )

    var annictId by AnnimeDao.annictId
    var title by AnnimeDao.title
    var media by AnnimeDao.media
    var seasonId by AnnimeDao.seasonId
    var officialSiteUrl by AnnimeDao.officialSiteUrl
    var twitterUserName by AnnimeDao.twitterUserName
    var imageUrl by AnnimeDao.imageUrl
}
