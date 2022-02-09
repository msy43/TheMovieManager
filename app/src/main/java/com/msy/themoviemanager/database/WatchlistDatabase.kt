package com.msy.themoviemanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [WatchlistEntity::class], version = 1)
abstract class WatchlistDatabase : RoomDatabase() {

    abstract fun watchlistDAO() : WatchListDAO

    companion object {
        private var instance: WatchlistDatabase? = null

        fun getWatchlistDatabase(context: Context): WatchlistDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    WatchlistDatabase::class.java,
                    "watchlist.db"
                ).allowMainThreadQueries().build()
            }
            return instance
        }
    }
}