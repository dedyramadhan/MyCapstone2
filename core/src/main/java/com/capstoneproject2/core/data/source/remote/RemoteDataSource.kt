package com.capstoneproject2.core.data.source.remote

import android.content.Context
import android.util.Log
import com.capstoneproject2.core.BuildConfig
import com.capstoneproject2.core.data.source.remote.network.ApiResponse
import com.capstoneproject2.core.data.source.remote.network.ApiService
import com.capstoneproject2.core.data.source.remote.response.MovieResponse
import com.capstoneproject2.core.di.CoreScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@CoreScope
class RemoteDataSource @Inject constructor(private val context: Context, private val apiService: ApiService){
    suspend fun getMovieDetail(movieId: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getDetailMovie(movieId, BuildConfig.tmdb_api_key)
                val movieList = ArrayList<MovieResponse>()
                movieList.add(response)
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(movieList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e:Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RDS on get movie detail", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getTrendingMovie(BuildConfig.tmdb_api_key)
                val movieList = response.results
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(movieList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RDS on get all movies", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


}