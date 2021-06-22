package com.capstoneproject2.core.domain.repository

import com.capstoneproject2.core.data.Resource
import com.capstoneproject2.core.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovies(): Flow<Resource<List<MovieModel>>>

    fun getMovieDetailByMovieId(movieId: Int): Flow<Resource<List<MovieModel>>>

    fun setFavoriteMovie(movieData: MovieModel, state: Boolean)

    fun getFavoriteMovie(): Flow<List<MovieModel>>

}