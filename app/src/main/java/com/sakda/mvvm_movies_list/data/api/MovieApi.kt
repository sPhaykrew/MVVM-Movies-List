package com.sakda.mvvm_movies_list.data.api

import com.sakda.mvvm_movies_list.data.model.MovieVideosList
import com.sakda.mvvm_movies_list.data.model.MoviesList
import com.sakda.mvvm_movies_list.utils.Constants.Companion.GET_MOVIES
import com.sakda.mvvm_movies_list.utils.Constants.Companion.GET_VIDEO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(GET_MOVIES)
    suspend fun getMovies(): Response<MoviesList>

    @GET(GET_MOVIES)
    suspend fun getCategory(@Query("with_genres") genresID : Int): Response<MoviesList>

    @GET(GET_VIDEO)
    suspend fun getVideo(@Path("movie_id") movieID : String) : Response<MovieVideosList>
}