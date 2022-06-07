package com.sakda.mvvm_movies_list.data.model

data class MoviesList(
    val page: Int,
    val movies: List<Movies>,
    val total_pages: Int,
    val total_results: Int
)