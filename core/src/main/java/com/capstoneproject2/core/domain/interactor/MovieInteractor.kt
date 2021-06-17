package com.capstoneproject2.core.domain.interactor

import com.capstoneproject2.core.data.Resource
import com.capstoneproject2.core.data.repository.MovieRepository
import com.capstoneproject2.core.domain.model.MovieModel
import com.capstoneproject2.core.domain.usecase.MoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieRepository): MoviesUseCase {

    override fun setFavoriteMovie(movieData: MovieModel, state: Boolean) = movieRepository.setFavoriteMovie(movieData, state)

    override fun getFavoriteMovie(): Flow<List<MovieModel>> = movieRepository.getFavoriteMovie()

    override fun getAllMovies(): Flow<Resource<List<MovieModel>>> = movieRepository.getAllMovies()

    override fun getMovieDetailByMovieId(movieId: Int): Flow<Resource<List<MovieModel>>> = movieRepository.getMovieDetailByMovieId(movieId)
}