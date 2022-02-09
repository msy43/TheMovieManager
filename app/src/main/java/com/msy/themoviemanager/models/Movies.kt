package com.msy.themoviemanager.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movies(

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("vote_average")
    val voteAverage: Number?,

) : Parcelable{
    constructor() : this("","",0,"", "","",0)
}