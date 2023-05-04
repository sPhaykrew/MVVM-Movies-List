package com.sakda.mvvm_movies_list.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.sakda.mvvm_movies_list.R
import com.sakda.mvvm_movies_list.data.model.MovieVideos
import com.sakda.mvvm_movies_list.databinding.FragmentMovieDetailsBinding
import com.sakda.mvvm_movies_list.ui.main.viewmodel.MovieViewModel


class MovieDetails : Fragment() {

    private val movieViewModel : MovieViewModel by activityViewModels()
    private val movie by navArgs<MovieDetailsArgs>()
    private var movieVideo = emptyList<MovieVideos>()
    private lateinit var fragmentMovieDetailsBinding : FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieDetailsBinding = FragmentMovieDetailsBinding.inflate(inflater,container,false)

        initToolbar()
        getMovie()
        initYouTubePlayerView()

        return fragmentMovieDetailsBinding.root
    }

    private fun initToolbar(){
        fragmentMovieDetailsBinding.toolbarSub.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_movie_details_to_mainFragment)
        }
    }

    private fun initYouTubePlayerView() {
        val youTubePlayerView: YouTubePlayerView = fragmentMovieDetailsBinding.youtube
        lifecycle.addObserver(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer) {
                super.onReady(youTubePlayer)
                val videoId = movieVideo[1].key
                youTubePlayer.cueVideo(videoId,0f)
            }
        })
    }

    private fun getMovie(){
        fragmentMovieDetailsBinding.movieName.text = movie.data.title
        fragmentMovieDetailsBinding.overview.text = movie.data.overview
        fragmentMovieDetailsBinding.releaseDate.text = "Release_date : " + movie.data.release_date
        fragmentMovieDetailsBinding.vote.text = "Vote : " + movie.data.vote_average.toString()
        val movieID = movie.data.id.toString()

        movieViewModel.getVideos(movieID).observe(viewLifecycleOwner, Observer {
            movieVideo = it.movieVideos
        })
    }
}