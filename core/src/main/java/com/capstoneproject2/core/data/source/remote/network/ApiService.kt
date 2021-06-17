package com.capstoneproject2.core.data.source.remote.network

import com.capstoneproject2.core.data.source.remote.response.ListMovieResponse
import com.capstoneproject2.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/{movieId}")
    suspend fun getDetailMovie(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String): MovieResponse

    @GET("3/trending/movie/week")
    suspend fun getTrendingMovie(@Query("api_key") apiKey: String): ListMovieResponse
}