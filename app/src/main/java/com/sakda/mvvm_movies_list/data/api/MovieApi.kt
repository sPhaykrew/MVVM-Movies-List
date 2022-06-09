package com.sakda.mvvm_movies_list.data.api

import com.sakda.mvvm_movies_list.data.model.MoviesList
import com.sakda.mvvm_movies_list.utils.Constants.Companion.GET_TRENDING
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET(GET_TRENDING)
    suspend fun getMovies(): Response<MoviesList>
}