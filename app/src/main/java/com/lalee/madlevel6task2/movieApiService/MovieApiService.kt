package com.lalee.madlevel6task2.movieApiService

import com.lalee.madlevel6task2.model.Movie
import com.lalee.madlevel6task2.model.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApiService {

    //TODO find the endpoint that you can insert popular movies with year
    @GET("3/movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String) : Call<MoviesList>
}