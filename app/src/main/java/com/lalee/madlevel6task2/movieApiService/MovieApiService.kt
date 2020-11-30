package com.lalee.madlevel6task2.movieApiService

import android.provider.Settings.Global.getString
import androidx.lifecycle.LiveData
import com.lalee.madlevel6task2.BuildConfig
import com.lalee.madlevel6task2.R
import com.lalee.madlevel6task2.model.Movie
import com.lalee.madlevel6task2.model.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiService {

    @GET("3/discover/movie?api_key=" + BuildConfig.MOVIE_API_KEY + "&sort_by=popularity.desc&include_adult=false&include_video=false")
    fun getPopularMovies(
        @Query("primary_release_year") releaseYear: String
    ): Call<MoviesList>
}
