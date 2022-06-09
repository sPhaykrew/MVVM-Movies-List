package com.sakda.mvvm_movies_list.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sakda.mvvm_movies_list.data.repository.Repository
import com.sakda.mvvm_movies_list.ui.main.viewmodel.MainViewModel

class MovieViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repository) as T
        }

        throw IllegalAccessException("Unknown View model Class")
    }
}