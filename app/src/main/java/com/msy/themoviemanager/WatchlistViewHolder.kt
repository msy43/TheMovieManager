package com.msy.themoviemanager

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.msy.themoviemanager.database.WatchlistEntity
import com.squareup.picasso.Picasso

class WatchlistViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.movie_item_desing2, viewGroup, false)) {

    private val title by lazy { itemView.findViewById<TextView>(R.id.movieFWTitle) }
    private val poster by lazy { itemView.findViewById<ImageView>(R.id.movieFWPoster) }

    fun bindTo(moviesWatchlist: WatchlistEntity, onItemClick: (view: View, moviesWatchlistDTO: WatchlistEntity) -> Unit) {

        title.text = moviesWatchlist.title

        val detailImageUrl = moviesWatchlist.posterPath
        val detailImageUrlFull = "https://image.tmdb.org/t/p/w500$detailImageUrl"

        Picasso.get().load(detailImageUrlFull).into(poster)

        itemView.setOnClickListener {


            val intent = Intent(it.context, DetailsScreen::class.java)


            intent.putExtra("movieTitle", moviesWatchlist.title)
            intent.putExtra("movieDate", moviesWatchlist.releaseDate)
            intent.putExtra("movieBackdropPath", moviesWatchlist.backdropPath)
            intent.putExtra("movieOverview",moviesWatchlist.overview)
            intent.putExtra("movieVoteAverage",moviesWatchlist.voteAverage)
            intent.putExtra("movieId",moviesWatchlist.movieId)
            intent.putExtra("moviePosterPath",moviesWatchlist.posterPath)

            it.context.startActivity(intent)

            onItemClick(it, WatchlistEntity(0,"","",0,"","","",""))
        }

    }

}