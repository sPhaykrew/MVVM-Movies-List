package com.sakda.mvvm_movies_list.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sakda.mvvm_movies_list.databinding.FragmentMainBinding
import com.sakda.mvvm_movies_list.ui.adapter.RecyclerViewAdapter
import com.sakda.mvvm_movies_list.ui.main.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var fragmentMainBinding: FragmentMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater,container,false)

        initRecyclerView()

        mainViewModel.movies.observe(viewLifecycleOwner, Observer {
            recyclerViewAdapter.setItem(it.movies)
            fragmentMainBinding.recyclerview.adapter = recyclerViewAdapter
            recyclerViewAdapter.notifyDataSetChanged()
        })

        return fragmentMainBinding.root
    }

    private fun initRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter()
        fragmentMainBinding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
    }

}