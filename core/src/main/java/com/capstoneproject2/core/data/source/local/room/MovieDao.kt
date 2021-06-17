package com.capstoneproject2.core.data.source.local.room

import androidx.room.*
import com.capstoneproject2.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM tb_movie WHERE movieId = :movieId")
    fun getMovieDetailByMovieId(movieId: Int): Flow<List<MovieEntity>>
    @Query("SELECT * FROM tb_movie")
    fun getAllMovies(): Flow<List<MovieEntity>>
    @Query("SELECT * FROM tb_movie WHERE isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)
    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
    @Query("UPDATE tb_movie SET isVisited = :isUpdated, todayDate = :todayDate WHERE movieId = :movieId")
    fun updateDetailMovie(movieId: Int, isUpdated: Boolean, todayDate: String)
    @Query("DELETE FROM tb_movie WHERE todayDate = :date")
    fun deleteMovie(date: String)
}