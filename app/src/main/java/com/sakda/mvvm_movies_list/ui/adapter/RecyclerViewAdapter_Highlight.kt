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
        holder.binding.apply {
            ImageMovies.loadImage(BASE_IMAGE + item[position].backdrop_path)
            Title.text = item[position].title
            root.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToMovieDetails(item[position])
                Navigation.findNavController(it).navigate(action)
            }
        }.also {
            if (it.Title.text.length > 26){
                it.Title.text = it.Title.text.substring(0,26) + "..."
            }
        }
    }

    override fun getItemCount(): Int = item.size

    inner class ViewHolder(val binding: RecyclerviewHighlightBinding) :
        RecyclerView.ViewHolder(binding.root)

}