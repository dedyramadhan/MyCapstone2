package com.capstoneproject2.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int,
    @ColumnInfo(name = "movieTitle")
    var movieTitle: String,
    @ColumnInfo(name = "movieDescription")
    var movieDescription: String,
    @ColumnInfo(name = "movieBackgroundImage")
    var movieBackgroundImage: String,
    @ColumnInfo(name = "movieLandscapeImage")
    var movieLandscapeImage: String,
    @ColumnInfo(name = "isVisited")
    var movieIsVisited: Boolean = false,
    @ColumnInfo(name = "isFavorite")
    var movieIsFavorite: Boolean = false,
    @ColumnInfo(name = "todayDate")
    var movieTodayDate: String,
    @ColumnInfo(name = "isHomeResult")
    var movieIsHomeResult: Boolean = true
): Parcelable