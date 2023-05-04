package com.sakda.mvvm_movies_list

import com.sakda.mvvm_movies_list.data.api.MovieApi
import com.sakda.mvvm_movies_list.data.model.Movies
import com.sakda.mvvm_movies_list.data.model.MoviesList
import com.sakda.mvvm_movies_list.data.repository.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Response

class MovieRepository {

    @Test
    fun getMovie() = runBlocking {
        val list = listOf(
            Movies(
                false,
                "/3CxUndGhUcZdt1Zggjdb2HkLLQX.jpg",
                listOf(28, 12, 878),
                640146,
                "en",
                "Ant-Man and the Wasp: Quantumania",
                "Super-Hero partners Scott Lang and Hope van Dyne, along with with Hope's parents Janet van Dyne and Hank Pym, and Scott's daughter Cassie Lang, find themselves exploring the Quantum Realm, interacting with strange new creatures and embarking on an adventure that will push them beyond the limits of what they thought possible.",
                6819.399,
                "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
                "2023-02-15",
                "Ant-Man and the Wasp: Quantumania",
                false,
                6.5,
                2108
            )
        )

        val movie = MoviesList(1, list, 38124, 762472)
        val api = Mockito.mock(MovieApi::class.java)
        `when`(api.getMovies()).thenReturn(Response.success(movie))
        val result = Repository(api).getMovies()

        Assert.assertEquals("Ant-Man and the Wasp: Quantumania", result.body()?.movies?.get(0)?.title)
    }
}