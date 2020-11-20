package com.lalee.madlevel6task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.lalee.madlevel6task2.R
import com.lalee.madlevel6task2.model.Movie
import com.lalee.madlevel6task2.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieDetailFragment : Fragment() {

    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeMovieDetail()
    }

    private fun observeMovieDetail() {
        movieViewModel.detailMovie.observe(viewLifecycleOwner, Observer {
            Glide.with(requireView().context)
                .load("https://image.tmdb.org/t/p/w300${it.imageBackdrop}")
                .into(iv_movie_backdrop)
            Glide.with(requireView().context)
                .load("https://image.tmdb.org/t/p/w200${it.imagePoster}")
                .into(iv_movie_poster)
            tv_movie_title.text = it.title
            tv_movie_releasedate.text = it.releaseDate
            tv_movie_rating.text = it.rating.toString()
            tv_movie_plot.text = it.overview
        })
    }
}