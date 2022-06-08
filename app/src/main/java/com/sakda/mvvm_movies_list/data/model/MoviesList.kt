package com.sakda.mvvm_movies_list.data.model

import com.google.gson.annotations.SerializedName

data class MoviesList(
    val page: Int,
    @SerializedName("results")
    val movies: List<Movies>,
    val total_pages: Int,
    val total_results: Int
)