package com.sakda.mvvm_movies_list.data.repository

import com.sakda.mvvm_movies_list.data.api.MovieApi
import com.sakda.mvvm_movies_list.data.api.RetrofitInstance

class Repository(private val movieApi: MovieApi) {

    suspend fun getMovies() = movieApi.getMovies()

    suspend fun getMoviesCategory(genresID : Int) = movieApi.getCategory(genresID)

    suspend fun getVideo(movieID : String) = movieApi.getVideo(movieID)

}