package com.sakda.mvvm_movies_list.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sakda.mvvm_movies_list.R
import com.sakda.mvvm_movies_list.data.api.RetrofitInstance
import com.sakda.mvvm_movies_list.data.repository.Repository
import com.sakda.mvvm_movies_list.databinding.ActivityMainBinding
import com.sakda.mvvm_movies_list.ui.base.MovieViewModelFactory
import com.sakda.mvvm_movies_list.ui.main.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var navController : NavController
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        initViewModel()

    }

    private fun initViewModel(){
        val repository = Repository(RetrofitInstance.getAPI())
        val factory = MovieViewModelFactory(repository)
        movieViewModel = ViewModelProvider(this,factory)[MovieViewModel::class.java]
    }

}