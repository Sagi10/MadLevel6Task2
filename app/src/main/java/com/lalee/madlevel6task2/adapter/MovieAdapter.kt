package com.lalee.madlevel6task2.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lalee.madlevel6task2.R
import com.lalee.madlevel6task2.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movies: List<Movie>, private val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.dataBind(movies[position], position + 1)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onClick(movies[adapterPosition])
            }
        }

        @SuppressLint("CheckResult")
        fun dataBind(movie: Movie, position: Int) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w300${movie.imagePoster}")
                .into(itemView.iv_item_movie_overview)
            itemView.tv_item_number.text = String.format("%s.", position.toString())
        }

    }
}