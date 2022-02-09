package com.msy.themoviemanager.services

import com.msy.themoviemanager.models.MoviesResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesSearchApiInterface {

    @GET("search/movie?api_key=58c53d2459b6de7679aa152efc9a6ca8&")

    fun getMovieList(
        @Query("query")query: String
    ): Call<MoviesResponse>

}