package com.sakda.mvvm_movies_list.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sakda.mvvm_movies_list.databinding.FragmentMainBinding
import com.sakda.mvvm_movies_list.ui.adapter.RecyclerViewAdapterMovies
import com.sakda.mvvm_movies_list.ui.adapter.RecyclerViewAdapter_Highlight
import com.sakda.mvvm_movies_list.ui.main.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private lateinit var fragmentMainBinding : FragmentMainBinding
    private lateinit var recyclerViewAdapterHighlight : RecyclerViewAdapter_Highlight
    private lateinit var recyclerviewAdapterAction : RecyclerViewAdapterMovies
    private lateinit var recyclerviewAdapterDrama : RecyclerViewAdapterMovies
    private lateinit var recyclerviewAdapterHorror : RecyclerViewAdapterMovies
    private lateinit var recyclerviewAdapterAnime : RecyclerViewAdapterMovies
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater,container,false)

        initRecyclerView()
        observeData()

        return fragmentMainBinding.root
    }

    private fun initRecyclerView() {
        recyclerViewAdapterHighlight = RecyclerViewAdapter_Highlight()
        fragmentMainBinding.recyclerviewHighlight.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)

        recyclerviewAdapterAction = RecyclerViewAdapterMovies()
        fragmentMainBinding.recyclerviewAction.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)

        recyclerviewAdapterDrama = RecyclerViewAdapterMovies()
        fragmentMainBinding.recyclerviewDrama.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)

        recyclerviewAdapterHorror = RecyclerViewAdapterMovies()
        fragmentMainBinding.recyclerviewHorror.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)

        recyclerviewAdapterAnime = RecyclerViewAdapterMovies()
        fragmentMainBinding.recyclerviewAnime.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
    }

    private fun observeData(){
        mainViewModel.movies.observe(viewLifecycleOwner, Observer {
            recyclerViewAdapterHighlight.setItem(it.movies)
            fragmentMainBinding.recyclerviewHighlight.adapter = recyclerViewAdapterHighlight
            recyclerViewAdapterHighlight.notifyDataSetChanged()
        })

        mainViewModel.moviesAction.observe(viewLifecycleOwner,Observer{
            recyclerviewAdapterAction.setItem(it.movies)
            fragmentMainBinding.recyclerviewAction.adapter = recyclerviewAdapterAction
            recyclerviewAdapterAction.notifyDataSetChanged()
        })

        mainViewModel.moviesDrama.observe(viewLifecycleOwner,Observer{
            recyclerviewAdapterDrama.setItem(it.movies)
            fragmentMainBinding.recyclerviewDrama.adapter = recyclerviewAdapterDrama
            recyclerviewAdapterDrama.notifyDataSetChanged()
        })

        mainViewModel.moviesHorror.observe(viewLifecycleOwner,Observer{
            recyclerviewAdapterHorror.setItem(it.movies)
            fragmentMainBinding.recyclerviewHorror.adapter = recyclerviewAdapterHorror
            recyclerviewAdapterHorror.notifyDataSetChanged()
        })

        mainViewModel.moviesAnime.observe(viewLifecycleOwner,Observer{
            recyclerviewAdapterAnime.setItem(it.movies)
            fragmentMainBinding.recyclerviewAnime.adapter = recyclerviewAdapterAnime
            recyclerviewAdapterAnime.notifyDataSetChanged()
        })

        mainViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        mainViewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                fragmentMainBinding.progressBar.visibility = View.VISIBLE
            } else {
                fragmentMainBinding.progressBar.visibility = View.GONE
            }
        })
    }

}