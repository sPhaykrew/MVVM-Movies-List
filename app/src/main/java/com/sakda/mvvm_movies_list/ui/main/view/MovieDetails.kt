package com.sakda.mvvm_movies_list.ui.main.view

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sakda.mvvm_movies_list.databinding.FragmentMovieDetailsBinding

class MovieDetails : Fragment() {

    private lateinit var fragmentMovieDetailsBinding : FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieDetailsBinding = FragmentMovieDetailsBinding.inflate(inflater,container,false)


        return fragmentMovieDetailsBinding.root
    }
}