package com.capstoneproject2.core.utils

import android.annotation.SuppressLint
import com.capstoneproject2.core.data.source.local.entity.MovieEntity
import com.capstoneproject2.core.data.source.remote.response.MovieResponse
import com.capstoneproject2.core.domain.model.MovieModel
import java.text.SimpleDateFormat
import java.util.*

object DataMapper {

    fun mapMovieResponsesToEntities(movieResponses: List<MovieResponse>): List<MovieEntity> = movieResponses.map {
        MovieEntity(
                movieId = it.id ?: 0,
                movieTitle = it.title ?: "No Title",
                movieDescription = it.description ?: "No Description",
                movieBackgroundImage = it.backgroundImage ?: "",
                movieLandscapeImage = it.landscapeImage ?: "",
                movieTodayDate = getTodayDate()
        )
    }

    @SuppressLint("SimpleDateFormat")
    fun getTodayDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date())
    }
    fun mapMovieEntitiesToDomains(movieEntities: List<MovieEntity>): List<MovieModel> = movieEntities.map {
        MovieModel(
                movieId = it.movieId,
                movieTitle = it.movieTitle,
                movieDescription = it.movieDescription,
                movieBackgroundImage = it.movieBackgroundImage,
                movieLandscapeImage = it.movieLandscapeImage,
                movieIsVisited = it.movieIsVisited,
                movieIsFavorite = it.movieIsFavorite,
                movieDate = it.movieTodayDate,
                movieIsHomeResult = it.movieIsHomeResult
        )
    }

    fun mapMovieDomainToEntity(input: MovieModel) = MovieEntity(
            movieId = input.movieId,
            movieTitle = input.movieTitle,
            movieDescription = input.movieDescription,
            movieBackgroundImage = input.movieBackgroundImage,
            movieLandscapeImage = input.movieLandscapeImage,
            movieIsVisited = input.movieIsVisited,
            movieIsFavorite = input.movieIsFavorite,
            movieTodayDate = input.movieDate,
            movieIsHomeResult = input.movieIsHomeResult
    )

}