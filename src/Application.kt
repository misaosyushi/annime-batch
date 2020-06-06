package com.annime.batch

import com.amazonaws.services.lambda.runtime.Context
import com.annime.batch.infrastructure.httpclient.AnnictClient
import com.typesafe.config.ConfigFactory
import io.ktor.config.HoconApplicationConfig
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database

// ローカルで動かしたいときはこっちを使う
//suspend fun main() {
//    initDB()
//    val client = AnnictClient()
//    client.requestAnnictAPI()
//}

// Lambdaのエントリーポイント
fun runMainAtLambda(context: Context) = runBlocking {
    initDB()
    startAnimeBatch(context)
}

suspend fun startAnimeBatch(context: Context) {
    val lambdaLogger = context.logger
    lambdaLogger.log("=========== start ===========")
    val client = AnnictClient()
    client.requestAnnictAPI()
    lambdaLogger.log("=========== finish ===========")

}

fun initDB() {
    val config = HoconApplicationConfig(ConfigFactory.load())
    Database.connect(
        config.property("db.url").getString(),
        config.property("db.driver").getString(),
        config.property("db.user").getString(),
        config.property("db.pass").getString()
    )
}

