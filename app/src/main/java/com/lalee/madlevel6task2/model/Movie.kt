package com.lalee.madlevel6task2.model

import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("title")var title: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("vote_average") var rating: Int,
    @SerializedName("overview")var overview: String,
    @SerializedName("backdrop_path") var imageBackdrop: String,
    @SerializedName("poster_path") var imagePoster: String
)