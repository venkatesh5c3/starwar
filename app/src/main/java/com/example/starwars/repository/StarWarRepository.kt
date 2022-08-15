package com.example.starwars.repository

class StarWarRepository {

    suspend fun getStarWarCharacters() =
        RetrofitInstance.api.getStarWarCharacters()
}