package com.sakda.mvvm_movies_list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakda.mvvm_movies_list.data.model.Movies
import com.sakda.mvvm_movies_list.databinding.RecyclerviewMoviesBinding
import com.sakda.mvvm_movies_list.utils.Constants
import com.sakda.mvvm_movies_list.utils.loadImage

class RecyclerViewAdapterMovies : RecyclerView.Adapter<RecyclerViewAdapterMovies.ViewHolder>() {

    private var item : List<Movies> = emptyList()

    fun setItem(item : List<Movies>){
        this.item = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapterMovies.ViewHolder {
        return ViewHolder(RecyclerviewMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapterMovies.ViewHolder, position: Int) {
        holder.binding.ImageMovies.loadImage(Constants.BASE_IMAGE + item[position].poster_path)
    }

    override fun getItemCount(): Int = item.size

    inner class ViewHolder(val binding: RecyclerviewMoviesBinding) : RecyclerView.ViewHolder(binding.root)

}