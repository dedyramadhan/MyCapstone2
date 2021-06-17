package com.capstoneproject2.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
        val movieId: Int,
        val movieTitle: String,
        val movieDescription: String,
        val movieBackgroundImage: String,
        val movieLandscapeImage: String,
        var movieIsVisited: Boolean,
        val movieIsFavorite: Boolean,
        val movieDate: String,
        val movieIsHomeResult: Boolean
): Parcelable