package com.capstoneproject2.core.domain.usecase

import com.capstoneproject2.core.data.Resource
import com.capstoneproject2.core.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {

    fun setFavoriteMovie(movieData: MovieModel, state: Boolean)

    fun getFavoriteMovie(): Flow<List<MovieModel>>

    fun getAllMovies(): Flow<Resource<List<MovieModel>>>

    fun getMovieDetailByMovieId(movieId: Int): Flow<Resource<List<MovieModel>>>
}