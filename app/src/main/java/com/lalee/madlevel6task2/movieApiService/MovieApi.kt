package com.lalee.madlevel6task2.movieApiService

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lalee.madlevel6task2.model.Movie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApi {
    companion object {

        private const val BASE_URL = "https://api.themoviedb.org/"

        fun createAPI(): MovieApiService {

            val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val gson = GsonBuilder().setPrettyPrinting().create()

            val movieApi = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return movieApi.create(MovieApiService::class.java)
        }
    }
}