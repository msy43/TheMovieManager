package com.msy.themoviemanager

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.msy.themoviemanager.database.FavoritesEntity
import com.squareup.picasso.Picasso

class FavoritesViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.movie_item_desing2, viewGroup, false)) {

    private val title by lazy { itemView.findViewById<TextView>(R.id.movieFWTitle) }
    private val poster by lazy { itemView.findViewById<ImageView>(R.id.movieFWPoster) }

    fun bindTo(moviesFavoritesList: FavoritesEntity, onItemClick: (view: View, moviesFavoritesDTO: FavoritesEntity) -> Unit) {

        title.text = moviesFavoritesList.title

        val detailImageUrl = moviesFavoritesList.posterPath
        val detailImageUrlFull = "https://image.tmdb.org/t/p/w500$detailImageUrl"

        Picasso.get().load(detailImageUrlFull).into(poster)

        itemView.setOnClickListener {


            val intent = Intent(it.context, DetailsScreen::class.java)


            intent.putExtra("movieTitle", moviesFavoritesList.title)
            intent.putExtra("movieDate", moviesFavoritesList.releaseDate)
            intent.putExtra("movieBackdropPath", moviesFavoritesList.backdropPath)
            intent.putExtra("movieOverview",moviesFavoritesList.overview)
            intent.putExtra("movieVoteAverage",moviesFavoritesList.voteAverage)
            intent.putExtra("movieId",moviesFavoritesList.movieId)
            intent.putExtra("moviePosterPath",moviesFavoritesList.posterPath)

            it.context.startActivity(intent)

            onItemClick(it, FavoritesEntity(0,"","",0,"","","",""))
        }

    }

}