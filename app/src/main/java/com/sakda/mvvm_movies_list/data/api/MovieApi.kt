package com.sakda.mvvm_movies_list.data.api

import com.sakda.mvvm_movies_list.data.model.MoviesList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("trending/movie/week?api_key=848b8839c1d5acdc50f7dba88178e0c5")
    suspend fun getMovies(): Response<MoviesList>
}