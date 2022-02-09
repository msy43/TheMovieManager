package com.msy.themoviemanager.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "watchlist")
data class WatchlistEntity(

    @PrimaryKey(autoGenerate = false)
    var key: Int = 0,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @ColumnInfo(name = "movieId")
    var movieId: Int = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: String,
)