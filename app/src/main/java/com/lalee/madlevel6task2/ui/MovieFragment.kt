package com.lalee.madlevel6task2.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lalee.madlevel6task2.R
import com.lalee.madlevel6task2.adapter.MovieAdapter
import com.lalee.madlevel6task2.model.Movie
import com.lalee.madlevel6task2.movieApiService.MovieApi
import com.lalee.madlevel6task2.movieApiService.MovieApiService
import com.lalee.madlevel6task2.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieFragment : Fragment() {

    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies)

    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_submit.setOnClickListener {
            movieViewModel.getPopularMovies(getString(R.string.API_KEY))
        }
        rv_movies_overview.adapter = movieAdapter

        observeMovieResults()
    }

    private fun observeMovieResults() {

    }
}