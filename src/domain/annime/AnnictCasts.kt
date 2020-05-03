package com.annime.batch.domain.annime

data class Casts(
    val casts: List<AnnictCast>
)

data class AnnictCast(
    val name: String,
    val character: Character
)

data class Character(
    val name: String
)
