package com.annime.batch.controller

import com.annime.batch.usecase.SeasonServiceImpl
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.sampleController() {

    val seasonService = SeasonServiceImpl()

    route("/sample") {
        get {
            call.respond(
                HttpStatusCode.OK,
                seasonService.findAll().map { season -> season.seasonText })
        }
        get("/detail") {
            call.respond(HttpStatusCode.OK, "sample detail routing ok")
        }
    }
}
