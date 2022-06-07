package com.sakda.mvvm_movies_list.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sakda.mvvm_movies_list.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var fragmentMainBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater,container,false)

        return fragmentMainBinding.root
    }

}