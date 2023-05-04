package com.sakda.mvvm_movies_list.ui.main.view

import android.annotation.SuppressLint
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
import com.sakda.mvvm_movies_list.ui.main.viewmodel.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private lateinit var fragmentMainBinding : FragmentMainBinding
    private lateinit var recyclerViewAdapterHighlight : RecyclerViewAdapter_Highlight
    private lateinit var recyclerviewAdapterAction : RecyclerViewAdapterMovies
    private lateinit var recyclerviewAdapterDrama : RecyclerViewAdapterMovies
    private lateinit var recyclerviewAdapterHorror : RecyclerViewAdapterMovies
    private lateinit var recyclerviewAdapterAnime : RecyclerViewAdapterMovies
    private val movieViewModel : MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater,container,false)

        initRecyclerView()
        observeData()
        autoScroll()

        return fragmentMainBinding.root
    }

    private fun initRecyclerView() {
        recyclerViewAdapterHighlight = RecyclerViewAdapter_Highlight()
        recyclerviewAdapterAction = RecyclerViewAdapterMovies()
        recyclerviewAdapterDrama = RecyclerViewAdapterMovies()
        recyclerviewAdapterHorror = RecyclerViewAdapterMovies()
        recyclerviewAdapterAnime = RecyclerViewAdapterMovies()

        fragmentMainBinding.apply {
            recyclerviewHighlight.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            recyclerviewAction.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            recyclerviewDrama.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            recyclerviewHorror.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
            recyclerviewAnime.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeData(){
        movieViewModel.apply {
            movies.observe(viewLifecycleOwner, Observer {
                recyclerViewAdapterHighlight.setItem(it.movies)
                fragmentMainBinding.recyclerviewHighlight.adapter = recyclerViewAdapterHighlight
            })

            moviesAction.observe(viewLifecycleOwner,Observer{
                recyclerviewAdapterAction.setItem(it.movies)
                fragmentMainBinding.recyclerviewAction.adapter = recyclerviewAdapterAction
            })

            moviesDrama.observe(viewLifecycleOwner,Observer{
                recyclerviewAdapterDrama.setItem(it.movies)
                fragmentMainBinding.recyclerviewDrama.adapter = recyclerviewAdapterDrama
            })

            moviesHorror.observe(viewLifecycleOwner,Observer{
                recyclerviewAdapterHorror.setItem(it.movies)
                fragmentMainBinding.recyclerviewHorror.adapter = recyclerviewAdapterHorror
            })

            moviesAnime.observe(viewLifecycleOwner,Observer{
                recyclerviewAdapterAnime.setItem(it.movies)
                fragmentMainBinding.recyclerviewAnime.adapter = recyclerviewAdapterAnime
            })

            errorMessage.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })

            loading.observe(viewLifecycleOwner, Observer {
                if (it) {
                    fragmentMainBinding.progressBar.visibility = View.VISIBLE
                } else {
                    fragmentMainBinding.progressBar.visibility = View.GONE
                }
            })
        }
    }

    private fun autoScroll(){
        CoroutineScope(Dispatchers.Main).launch {
            while (true){
                val position = (fragmentMainBinding.recyclerviewHighlight.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()!!
                if(position == recyclerViewAdapterHighlight.itemCount - 1) {
                    fragmentMainBinding.recyclerviewHighlight.smoothScrollToPosition(0)
                    delay(6000L)
                } else {
                    fragmentMainBinding.recyclerviewHighlight.smoothScrollToPosition(position + 1)
                    delay(6000L)
                }
            }
        }
    }

}