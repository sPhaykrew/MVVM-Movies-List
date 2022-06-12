package com.sakda.mvvm_movies_list.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sakda.mvvm_movies_list.data.repository.Repository
import com.sakda.mvvm_movies_list.ui.main.viewmodel.MovieViewModel

class MovieViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel(repository) as T
        }

        throw IllegalAccessException("Unknown View model Class")
    }
}