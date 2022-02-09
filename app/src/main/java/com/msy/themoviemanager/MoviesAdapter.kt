package com.msy.themoviemanager

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msy.themoviemanager.models.Movies


class MoviesAdapter (private var movies: List<Movies> , private val onItemClick: (view: View, moviesDTO: Movies) -> Unit) : RecyclerView.Adapter<MovieViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(movies[position])
        { view, moviesDTO -> onItemClick(view, moviesDTO)}

    }
}