package com.sakda.mvvm_movies_list

import com.sakda.mvvm_movies_list.data.api.MovieApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieAPITest {

    private lateinit var mockWebServer : MockWebServer
    private lateinit var movieAPI : MovieApi

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        movieAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Test
    fun getMovie_Null() = runBlocking {
        val mockResponse = MockResponse()
        mockResponse.setBody("{}")
        mockWebServer.enqueue(mockResponse)
        val response = movieAPI.getMovies()
        Assert.assertEquals(true,response.body()?.movies.isNullOrEmpty())
    }


    @Test
    fun getMovie_Succ() = runBlocking {
        val mockResponse = MockResponse()
        val content = HelperJson.readFileResource("/getMovie.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = movieAPI.getMovies()
        mockWebServer.takeRequest()

        Assert.assertEquals(false,response.body()?.movies?.isEmpty())
        Assert.assertEquals("Ant-Man and the Wasp: Quantumania", response.body()?.movies?.get(0)?.title)
    }

    @Test
    fun testError() = runBlocking {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        val response = movieAPI.getMovies()
        mockWebServer.takeRequest()

        Assert.assertEquals(false,response.isSuccessful)
        Assert.assertEquals(404,response.code())
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

}