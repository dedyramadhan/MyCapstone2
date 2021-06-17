package com.capstoneproject2.core.data.source.local

import com.capstoneproject2.core.data.source.local.entity.MovieEntity
import com.capstoneproject2.core.data.source.local.room.MovieDao
import com.capstoneproject2.core.di.CoreScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@CoreScope
class LocalDataSource @Inject constructor(private val movieDao: MovieDao){
    suspend fun insertMovie(movieList: List<MovieEntity>) { movieDao.insertMovies(movieList)}
    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()
    fun getMovieDetailByMovieId(movieId: Int): Flow<List<MovieEntity>> = movieDao.getMovieDetailByMovieId(movieId)
    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.movieIsFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()
    fun setUpdateDetailMovie(movie: MovieEntity) {
        movieDao.updateDetailMovie(movie.movieId, true, movie.movieTodayDate)
    }
    fun deleteMovie(date: String) { movieDao.deleteMovie(date) }
}