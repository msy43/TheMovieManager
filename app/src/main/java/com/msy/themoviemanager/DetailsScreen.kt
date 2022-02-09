package com.msy.themoviemanager

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.msy.themoviemanager.database.FavoritesDatabase
import com.msy.themoviemanager.database.FavoritesEntity
import com.msy.themoviemanager.database.WatchlistDatabase
import com.msy.themoviemanager.database.WatchlistEntity
import com.msy.themoviemanager.databinding.ActivityDetailsScreenBinding
import com.squareup.picasso.Picasso

class DetailsScreen : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsScreenBinding

    var key: Int = 0
    private var favorite: Boolean = false
    private var watchlist: Boolean = false
    private var movieTitle: String = ""
    private var movieDate: String = ""
    private var movieOverview: String = ""
    private var movieVoteAverage: String = ""
    private var moviePosterPath: String = ""
    var movieId: Int = 0

    private lateinit var favoritesMenuItem: MenuItem
    private lateinit var watchlistMenuItem: MenuItem

    private lateinit var favoritesMovie: FavoritesEntity
    private lateinit var watchlistMovie: WatchlistEntity

    private lateinit var favoritesDatabase: FavoritesDatabase
    private lateinit var watchlistDatabase: WatchlistDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        val view = binding.root

        favoritesDatabase = FavoritesDatabase.getFavoritesDatabase(this)!!
        watchlistDatabase = WatchlistDatabase.getWatchlistDatabase(this)!!

        val toolbar = binding.detailsToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.showOverflowMenu()


        key = intent.extras?.get("movieId") as Int

        movieTitle = intent.extras?.get("movieTitle").toString()

        movieDate = intent.extras?.get("movieDate").toString()

        movieOverview = intent.extras?.get("movieOverview").toString()

        movieVoteAverage = intent.extras?.get("movieVoteAverage").toString()

        movieId = intent.extras?.get("movieId") as Int

        moviePosterPath = intent.extras?.get("moviePosterPath").toString()

        binding.movieTitle.text = movieTitle
        binding.movieDate.text = movieDate
        binding.movieOverview.text = movieOverview
        binding.movieVoteAverage.text = movieVoteAverage

        val detailImageUrl = intent.extras?.get("movieBackdropPath").toString()
        val detailImageUrlFull = "https://image.tmdb.org/t/p/w500$detailImageUrl"


        favoritesMovie = FavoritesEntity(key,detailImageUrl,moviePosterPath,movieId,movieTitle,movieDate,movieOverview,movieVoteAverage)
        watchlistMovie = WatchlistEntity(key,detailImageUrl,moviePosterPath,movieId,movieTitle,movieDate,movieOverview,movieVoteAverage)

        Picasso.get().load(detailImageUrlFull).into(binding.movieBackDrop)

        back()

        setContentView(view)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        favoritesMenuItem = menu!!.findItem(R.id.appbarAddFavorite)
        watchlistMenuItem = menu.findItem(R.id.appbarAddWatchlist)
        checkIsFavorites(key)
        checkIsWatchlist(key)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {

        R.id.appbarAddFavorite -> {
            if (favorite) {
                item.setIcon(R.drawable.ic_baseline_favorite_border_24)
                favorite = false
                deleteFavoritesDatabase(key)
            }else{
                item.setIcon(R.drawable.ic_round_favorite_24)
                favorite = true
                insertFavoritesDatabase()
            }
            true
        }

        R.id.appbarAddWatchlist -> {
            if (watchlist) {
                item.setIcon(R.drawable.ic_round_playlist_add_24)
                watchlist = false
                deleteWatchListDatabase(key)

            }else{
                item.setIcon(R.drawable.ic_round_playlist_remove_24)
                watchlist = true
                insertWatchlistDatabase()
            }

            true
        }
        else -> {

            super.onOptionsItemSelected(item)
        }

    }

    private fun deleteFavoritesDatabase(key: Int){
        favoritesDatabase.favoritesDAO().deleteWithId(key)
        Toast.makeText(this, "$movieTitle deleted from your favorites!", Toast.LENGTH_SHORT).show()
    }

    private fun insertFavoritesDatabase(){
        favoritesDatabase.favoritesDAO().insert(favoritesMovie)
        Toast.makeText(this, "$movieTitle added to your favorites!", Toast.LENGTH_SHORT).show()
    }

    private fun insertWatchlistDatabase(){
        watchlistDatabase.watchlistDAO().insert(watchlistMovie)
        Toast.makeText(this, "$movieTitle added to your watchlist!", Toast.LENGTH_SHORT).show()
    }

    private fun deleteWatchListDatabase(key: Int){
        watchlistDatabase.watchlistDAO().deleteWithId(key)
        Toast.makeText(this, "$movieTitle deleted from your watchlist!", Toast.LENGTH_SHORT).show()
    }

    private fun checkIsFavorites(key: Int){
        val exist = favoritesDatabase.favoritesDAO().checkisFavorites(key)
        if (exist){
            favoritesMenuItem.setIcon(R.drawable.ic_round_favorite_24)
            favorite = true
        }else{
            favoritesMenuItem.setIcon(R.drawable.ic_baseline_favorite_border_24)
            favorite = false
        }
    }

    private fun checkIsWatchlist(key: Int){
        val exist = watchlistDatabase.watchlistDAO().checkisWatchlist(key)
        if (exist){
            watchlistMenuItem.setIcon(R.drawable.ic_round_playlist_remove_24)
            watchlist = true
        }else{
            watchlistMenuItem.setIcon(R.drawable.ic_round_playlist_add_24)
            watchlist = false
        }
    }

    private fun back(){
        binding.detailsToolbarBack.setOnClickListener {
            finish()
        }
    }
}