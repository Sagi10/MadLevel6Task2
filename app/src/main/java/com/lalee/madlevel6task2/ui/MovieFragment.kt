package com.lalee.madlevel6task2.ui

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lalee.madlevel6task2.R
import com.lalee.madlevel6task2.adapter.MovieAdapter
import com.lalee.madlevel6task2.model.Movie
import com.lalee.madlevel6task2.model.MoviesList
import com.lalee.madlevel6task2.movieApiService.MovieApi
import com.lalee.madlevel6task2.movieApiService.MovieApiService
import com.lalee.madlevel6task2.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieFragment : Fragment() {

    private val movies = arrayListOf<Movie>()
    private lateinit var movieAdapter: MovieAdapter

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
        //dont show progres bar if submit is not clicked
        pb_loading_movies.isVisible = false

        movieAdapter = MovieAdapter(movies, ::onMovieClick)
        rv_movies_overview.adapter = movieAdapter

        btn_submit.setOnClickListener {
            movieViewModel.getPopularMovies(tv_input_year.text.toString())
            pb_loading_movies.isVisible = true
        }

        observeMovieResults()
    }

    private fun onMovieClick(movie: Movie) {
        movieViewModel.sendMovieToDetail(movie)
        findNavController().navigate(R.id.action_MovieFragment_to_MovieDetailFragment)
    }

    private fun observeMovieResults() {
        movieViewModel.movies.observe(viewLifecycleOwner, {
            this@MovieFragment.movies.clear()
            this@MovieFragment.movies.addAll(it.movieList)
            pb_loading_movies.isVisible = false
            movieAdapter.notifyDataSetChanged()
        })

        movieViewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(activity, "ERROR: $it", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "ERROR: $it")
        })
    }
}