package com.sakda.mvvm_movies_list.data.model

import com.google.gson.annotations.SerializedName

data class MovieVideosList(
    val id: Int,
    @SerializedName("results")
    val movieVideos: List<MovieVideos>
)