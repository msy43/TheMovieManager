package com.msy.themoviemanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoritesEntity::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun favoritesDAO() : FavoritesDAO

    companion object {
        private var instance: FavoritesDatabase? = null

        fun getFavoritesDatabase(context: Context): FavoritesDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    FavoritesDatabase::class.java,
                    "favorites.db"
                ).allowMainThreadQueries().build()
            }
            return instance
        }
    }

}