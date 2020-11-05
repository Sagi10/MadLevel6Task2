package com.lalee.madlevel6task2.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lalee.madlevel6task2.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(application: Application): AndroidViewModel(application) {

    private val movieRepository = MovieRepository()
    private val _error = MutableLiveData<String>()

    val movies = movieRepository.movies
    val error = _error

    fun getPopularMovies(){
        viewModelScope.launch {
            try {
                movieRepository.getPopulairMovies()
            } catch (error: MovieRepository.MovieError){
                _error.value = error.message
                Log.d(TAG, error.message.toString())
            }
        }
    }
}