package com.lalee.madlevel6task2.movieApiService

import com.lalee.madlevel6task2.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApiService {

    @GET("3/movie/popular")
    suspend fun getPopulairMovies(@Query("api_key") apiKey: String) : Movie
}