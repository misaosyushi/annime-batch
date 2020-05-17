package com.annime.batch

import com.amazonaws.services.lambda.runtime.Context
import com.annime.batch.infrastructure.httpclient.AnnictClient
import org.jetbrains.exposed.sql.Database

suspend fun main() {
    Database.connect("jdbc:mysql://0.0.0.0:13306/annime?useSSL=false", "com.mysql.jdbc.Driver", "annime", "annime")

    val client = AnnictClient()
    client.requestAnnictAPI()
}

class LambdaEntry {
    // lambda用のエントリーポイント
    suspend fun runMainAtLambda(context: Context) {
        val lambdaLogger = context.logger
        lambdaLogger.log("=========== start ===========")
        main()
        lambdaLogger.log("=========== finish ===========")
    }
}

