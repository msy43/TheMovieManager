package com.msy.themoviemanager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WatchListDAO {

    @Insert
    fun insert(watchlist: WatchlistEntity)

    @Query("DELETE FROM watchlist WHERE `key` = :key")
    fun deleteWithId(key: Int)

    @Query("SELECT EXISTS (SELECT 1 FROM watchlist WHERE `key`= :key)")
    fun checkisWatchlist(key: Int) : Boolean

    @Delete
    fun delete(watchlist: WatchlistEntity)

    @Query("SELECT*FROM watchlist")
    fun getAllMovies(): List<WatchlistEntity>

}