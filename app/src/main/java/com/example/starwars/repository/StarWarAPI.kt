package com.example.starwars.repository

import com.example.starwars.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarAPI {

    @GET("api/people/")
    suspend fun getStarWarCharacters(
        @Query("format")
        format : String = "json"
    ) : Response<CharactersResponse>
}