package com.sakda.mvvm_movies_list.utils

class Constants {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val GET_MOVIES = "movie/popular?api_key=848b8839c1d5acdc50f7dba88178e0c5&language=en-US&page=1"
        const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500"
        //Category
        const val Action = 28
        const val Adventure = 12
        const val Animation = 16
        const val Comedy = 35
        const val Crime = 80
        const val Documentary  = 99
        const val Drama = 18
        const val Family = 10751
        const val Fantasy  = 14
        const val History = 36
        const val Horror = 27
        const val Music = 10402
        const val Mystery = 9648
        const val Romance = 10749
        const val Science_Fiction = 878
        const val TV_Movie = 10770
        const val Thriller = 53
        const val War = 10752
        const val Western = 37
    }
}