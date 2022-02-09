package com.msy.themoviemanager

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.msy.themoviemanager.models.Movies

class MovieViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.movie_item_desing, viewGroup, false)) {

    private val title by lazy { itemView.findViewById<TextView>(R.id.movieTitle) }
    private val date by lazy { itemView.findViewById<TextView>(R.id.movieDate) }

    fun bindTo(movies: Movies, onItemClick: (view: View, moviesDTO: Movies) -> Unit) {

        title.text = movies.title
        date.text = movies.releaseDate!!.take(4)

        itemView.setOnClickListener {


            val intent = Intent(it.context, DetailsScreen::class.java)


            intent.putExtra("movieTitle", movies.title)
            intent.putExtra("movieDate", movies.releaseDate)
            intent.putExtra("movieBackdropPath", movies.backdropPath)
            intent.putExtra("movieOverview",movies.overview)
            intent.putExtra("movieVoteAverage",movies.voteAverage)
            intent.putExtra("movieId",movies.id)
            intent.putExtra("moviePosterPath",movies.posterPath)

            it.context.startActivity(intent)

            onItemClick(it, Movies())
        }
    }

}

