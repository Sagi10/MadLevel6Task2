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
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {

    private val movieApiService: MovieApiService = MovieApi.createAPI()

    private val _movies: MutableLiveData<Call<MoviesList>> = MutableLiveData()

    val movies : LiveData<Call<MoviesList>> get() = _movies

    fun getPopularMovies(apiKey: String){
        try {

            val movieResult = movieApiService.getPopularMovies(apiKey)

            movieResult.enqueue(object: Callback<MoviesList>{
                override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {


                    Log.d(TAG, "DIT IS DE RESULT: ${response.body().toString()}}")
                }

                override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                    Log.e(TAG, "ER IS EEN ERROR: ${t.message}}")

                }
            })
        } catch (error: Throwable){
            throw MovieError("NIET GELUKT OM MOVIE OP TE HALEN", error)
        }
    }

    class MovieError(message: String, cause: Throwable) : Throwable(message, cause)
}