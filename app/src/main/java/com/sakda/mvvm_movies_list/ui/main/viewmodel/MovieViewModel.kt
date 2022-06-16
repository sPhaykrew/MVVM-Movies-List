package com.sakda.mvvm_movies_list.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakda.mvvm_movies_list.data.model.MovieVideosList
import com.sakda.mvvm_movies_list.data.model.MoviesList
import com.sakda.mvvm_movies_list.data.repository.Repository
import com.sakda.mvvm_movies_list.utils.Constants.Companion.Action
import com.sakda.mvvm_movies_list.utils.Constants.Companion.Animation
import com.sakda.mvvm_movies_list.utils.Constants.Companion.Drama
import com.sakda.mvvm_movies_list.utils.Constants.Companion.Horror
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(private val repository: Repository) : ViewModel() {

    val movies : MutableLiveData<MoviesList> = MutableLiveData()
    val moviesAction : MutableLiveData<MoviesList> = MutableLiveData()
    val moviesDrama : MutableLiveData<MoviesList> = MutableLiveData()
    val moviesHorror : MutableLiveData<MoviesList> = MutableLiveData()
    val moviesAnime : MutableLiveData<MoviesList> = MutableLiveData()
    val movieVideos : MutableLiveData<MovieVideosList> = MutableLiveData()

    val errorMessage : MutableLiveData<String> = MutableLiveData()
    val loading : MutableLiveData<Boolean> = MutableLiveData()

    init {
        getMovies()
        getMoviesAction()
        getMoviesDrama()
        getMoviesHorror()
        getMoviesAnime()
    }

    private fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getMovies()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    movies.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun getMoviesAction(){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getMoviesCategory(Action)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    moviesAction.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun getMoviesDrama(){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getMoviesCategory(Drama)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    moviesDrama.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun getMoviesHorror(){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getMoviesCategory(Horror)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    moviesHorror.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun getMoviesAnime(){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getMoviesCategory(Animation)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    moviesAnime.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    fun getVideos(movieID : String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getVideo(movieID)
            withContext(Dispatchers.Main){
                if (response.isSuccessful) {
                    movieVideos.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

}

