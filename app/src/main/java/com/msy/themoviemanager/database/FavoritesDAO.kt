package com.msy.themoviemanager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavoritesDAO {

    @Insert
    fun insert(favorites: FavoritesEntity)

    @Query("DELETE FROM favorites WHERE `key` = :key")
    fun deleteWithId(key: Int)

    @Query("SELECT EXISTS (SELECT 1 FROM favorites WHERE `key`= :key)")
    fun checkisFavorites(key: Int) : Boolean

    @Delete
    fun delete(favorites: FavoritesEntity)

    @Query("SELECT*FROM favorites")
    fun getAllMovies(): List<FavoritesEntity>

}