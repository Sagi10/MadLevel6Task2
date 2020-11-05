package com.lalee.madlevel6task2.repository

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lalee.madlevel6task2.model.Movie
import com.lalee.madlevel6task2.model.MoviesList
import com.lalee.madlevel6task2.movieApiService.MovieApi
import com.lalee.madlevel6task2.movieApiService.MovieApiService
import retrofit2.Call

class MovieRepository {

    private val movieApiService: MovieApiService = MovieApi.createAPI()

    private val _movies: MutableLiveData<Call<MoviesList>> = MutableLiveData()

    val movies : LiveData<Call<MoviesList>> get() = _movies

    suspend fun getPopularMovies(apiKey: String){
        try {
            //TODO fix this. it returns null
            val movieResult = movieApiService.getPopularMovies(apiKey)

            _movies.value = movieResult
            Log.d(TAG, "DIT IS DE RESULT: $movieResult")

        } catch (error: Throwable){
            throw MovieError("NIET GELUKT OM MOVIE OP TE HALEN", error)
        }
    }

    class MovieError(message: String, cause: Throwable) : Throwable(message, cause)
}