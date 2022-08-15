package com.example.starwars.model

data class CharactersResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: MutableList<Result>
)