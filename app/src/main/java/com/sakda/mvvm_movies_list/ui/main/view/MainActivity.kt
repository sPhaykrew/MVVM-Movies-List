package com.sakda.mvvm_movies_list.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.graphics.green
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sakda.mvvm_movies_list.R
import com.sakda.mvvm_movies_list.data.api.MovieApi
import com.sakda.mvvm_movies_list.data.api.RetrofitInstance
import com.sakda.mvvm_movies_list.data.repository.Repository
import com.sakda.mvvm_movies_list.databinding.ActivityMainBinding
import com.sakda.mvvm_movies_list.ui.base.MovieViewModelFactory
import com.sakda.mvvm_movies_list.ui.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var navController : NavController
    private lateinit var movieViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        initViewModel()

        movieViewModel.movies.observe(this,Observer {
            val name = it.movies[0].original_title
            Log.i("movie name", name)
        })

    }

    private fun initViewModel(){
        val repository = Repository(RetrofitInstance.getAPI())
        val factory = MovieViewModelFactory(repository)
        movieViewModel = ViewModelProvider(this,factory)[MainViewModel::class.java]
    }

}