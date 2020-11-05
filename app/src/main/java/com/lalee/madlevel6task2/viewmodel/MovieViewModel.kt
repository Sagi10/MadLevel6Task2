package com.lalee.madlevel6task2.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lalee.madlevel6task2.model.Movie
import com.lalee.madlevel6task2.model.PopularMovies
import com.lalee.madlevel6task2.repository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Call

class MovieViewModel(application: Application): AndroidViewModel(application) {

    private val movieRepository = MovieRepository()
    private val _error = MutableLiveData<String>()
    private val _succes = MutableLiveData<Boolean>()

    val movies: LiveData<Movie> = movieRepository.movies
    val error = _error
    val succes = _succes

    fun getPopularMovies(apiKey: String){
        viewModelScope.launch {
            try {
                movieRepository.getPopularMovies(apiKey)
                _succes.value = true
            } catch (error: MovieRepository.MovieError){
                _error.value = error.message
                Log.d(TAG, error.message.toString())
            }
        }
    }
}