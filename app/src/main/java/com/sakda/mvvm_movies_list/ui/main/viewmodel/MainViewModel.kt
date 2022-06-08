package com.sakda.mvvm_movies_list.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakda.mvvm_movies_list.data.model.MoviesList
import com.sakda.mvvm_movies_list.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: Repository) : ViewModel() {

    val movies : MutableLiveData<MoviesList> = MutableLiveData()

    init {
        getMovies()
    }

    private fun getMovies(){
        viewModelScope.launch(Dispatchers.IO){
           val response = repository.getMovies()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    movies.postValue(response.body())
                } else {
                    Log.i("Error",response.message())
                }
            }
        }
    }

}