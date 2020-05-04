package com.annime.batch

import com.annime.batch.infrastructure.httpclient.AnnictClient
import org.jetbrains.exposed.sql.Database

suspend fun main(args: Array<String>) {
    Database.connect("jdbc:mysql://0.0.0.0:13306/annime?useSSL=false", "com.mysql.jdbc.Driver", "annime", "annime")

    val client = AnnictClient()
    client.requestAnnictAPI()
}

