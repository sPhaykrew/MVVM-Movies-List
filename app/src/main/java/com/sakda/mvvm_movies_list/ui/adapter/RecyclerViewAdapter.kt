package com.sakda.mvvm_movies_list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakda.mvvm_movies_list.data.model.Movies
import com.sakda.mvvm_movies_list.databinding.RecyclerviewItemBinding
import com.sakda.mvvm_movies_list.utils.loadImage

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var item : List<Movies> = emptyList()

    fun setItem(item : List<Movies>){
        this.item = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        return ViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.binding.ImageMovies.loadImage("https://image.tmdb.org/t/p/w500" + item[position].backdrop_path)
    }

    override fun getItemCount(): Int = item.size

    inner class ViewHolder(val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}