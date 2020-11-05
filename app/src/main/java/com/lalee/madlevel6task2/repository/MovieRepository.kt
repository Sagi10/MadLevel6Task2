package com.lalee.madlevel6task2.repository

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lalee.madlevel6task2.model.Movie
import com.lalee.madlevel6task2.movieApiService.MovieApi
import com.lalee.madlevel6task2.movieApiService.MovieApiService

class MovieRepository {

    private val movieApiService: MovieApiService = MovieApi.createAPI()

    private val _movies: MutableLiveData<Movie> = MutableLiveData()

    val movies : LiveData<Movie> get() = _movies

    suspend fun getPopulairMovies(){
        try {

            val movieResult = movieApiService.getPopulairMovies()
            _movies.value = movieResult

        } catch (error: Throwable){
            throw MovieError("NIET GELUKT OM MOVIE OP TE HALEN", error)
        }
    }

    class MovieError(message: String, cause: Throwable) : Throwable(message, cause)
}