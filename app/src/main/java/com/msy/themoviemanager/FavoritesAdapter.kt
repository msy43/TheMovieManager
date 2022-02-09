package com.msy.themoviemanager

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msy.themoviemanager.database.FavoritesEntity

class FavoritesAdapter(private var moviesFavorites: List<FavoritesEntity>, private val onItemClick: (view: View, moviesFavoritesDTO: FavoritesEntity) -> Unit) : RecyclerView.Adapter<FavoritesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(parent)
    }


    override fun getItemCount(): Int = moviesFavorites.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {

        holder.bindTo(moviesFavorites[position])
        {view, moviesFavoritesDTO -> onItemClick(view,moviesFavoritesDTO) }

    }
}