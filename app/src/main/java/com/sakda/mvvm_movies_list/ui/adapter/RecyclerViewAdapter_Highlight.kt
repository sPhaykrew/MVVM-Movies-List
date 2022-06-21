package com.sakda.mvvm_movies_list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sakda.mvvm_movies_list.data.model.Movies
import com.sakda.mvvm_movies_list.databinding.RecyclerviewHighlightBinding
import com.sakda.mvvm_movies_list.ui.main.view.MainFragmentDirections
import com.sakda.mvvm_movies_list.utils.Constants.Companion.BASE_IMAGE
import com.sakda.mvvm_movies_list.utils.loadImage

class RecyclerViewAdapter_Highlight : RecyclerView.Adapter<RecyclerViewAdapter_Highlight.ViewHolder>() {

    private var item : List<Movies> = emptyList()

    fun setItem(item : List<Movies>){
        this.item = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter_Highlight.ViewHolder {
        return ViewHolder(RecyclerviewHighlightBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter_Highlight.ViewHolder, position: Int) {
        holder.binding.ImageMovies.loadImage(BASE_IMAGE + item[position].backdrop_path)
        var title = item[position].title
        if(title.length > 30) {
            title = title.substring(0,30) + "..."
        }
        holder.binding.Title.text = title
        holder.binding.root.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToMovieDetails(item[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int = item.size

    inner class ViewHolder(val binding: RecyclerviewHighlightBinding) :
        RecyclerView.ViewHolder(binding.root)

}