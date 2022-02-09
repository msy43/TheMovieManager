package com.msy.themoviemanager

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msy.themoviemanager.database.WatchlistEntity

class WatchlistAdapter(private var moviesWatchlist: List<WatchlistEntity>, private val onItemClick: (view: View, moviesWatchlistDTO: WatchlistEntity) -> Unit) : RecyclerView.Adapter<WatchlistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistViewHolder {
        return WatchlistViewHolder(parent)
    }

    override fun getItemCount(): Int = moviesWatchlist.size

    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
        holder.bindTo(moviesWatchlist[position])
        {view, moviesWatchlistDTO -> onItemClick(view, moviesWatchlistDTO) }
    }
}