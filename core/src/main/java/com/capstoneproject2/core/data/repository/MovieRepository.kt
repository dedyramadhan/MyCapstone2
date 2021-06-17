package com.capstoneproject2.core.data.repository

import com.capstoneproject2.core.data.NetworkBoundResource
import com.capstoneproject2.core.data.Resource
import com.capstoneproject2.core.data.source.local.LocalDataSource
import com.capstoneproject2.core.data.source.remote.RemoteDataSource
import com.capstoneproject2.core.data.source.remote.network.ApiResponse
import com.capstoneproject2.core.data.source.remote.response.MovieResponse
import com.capstoneproject2.core.di.CoreScope
import com.capstoneproject2.core.domain.model.MovieModel
import com.capstoneproject2.core.domain.repository.IMovieRepository
import com.capstoneproject2.core.utils.AppExecutors
import com.capstoneproject2.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@CoreScope
class MovieRepository @Inject constructor(
        private val localData: LocalDataSource,
        private val remoteData: RemoteDataSource,
        private val appExecutors: AppExecutors
): IMovieRepository{


    override fun getMovieDetailByMovieId(movieId: Int): Flow<Resource<List<MovieModel>>> =
        object : NetworkBoundResource<List<MovieModel>, List<MovieResponse>>() {


            override fun loadFromDB(): Flow<List<MovieModel>> {
                return localData.getMovieDetailByMovieId(movieId).map { DataMapper.mapMovieEntitiesToDomains(it) }
            }

            override fun shouldFetch(data: List<MovieModel>?): Boolean {
                var isFetch = true
                if (data != null) {
                    isFetch = data[0].movieIsVisited == false
                }
                return isFetch
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> = remoteData.getMovieDetail(movieId)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieEntities = DataMapper.mapMovieResponsesToEntities(data)
                for (movie in movieEntities) {
                    appExecutors.diskIO().execute { localData.setUpdateDetailMovie(movie) }
                }
            }
        }.asFlow()

    override fun getAllMovies(): Flow<Resource<List<MovieModel>>> =
            object : NetworkBoundResource<List<MovieModel>, List<MovieResponse>>() {

                override fun loadFromDB(): Flow<List<MovieModel>> {
                    return localData.getAllMovies().map { DataMapper.mapMovieEntitiesToDomains(it) }
                }

                override fun shouldFetch(data: List<MovieModel>?): Boolean {
                    var isFetch = true
                    if (data != null) {
                        if (data.isNotEmpty()) {
                            if (data[0].movieDate == DataMapper.getTodayDate()) {
                                isFetch = false
                            } else {
                                appExecutors.diskIO().execute { localData.deleteMovie(data[0].movieDate) }
                            }
                        }
                    }
                    return isFetch
                }

                override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> = remoteData.getAllMovies()

                override suspend fun saveCallResult(data: List<MovieResponse>) {
                    val movieList = DataMapper.mapMovieResponsesToEntities(data)
                    localData.insertMovie(movieList)
                }
            }.asFlow()

    override fun setFavoriteMovie(movieData: MovieModel, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movieData)
        appExecutors.diskIO().execute { localData.setFavoriteMovie(movieEntity, state) }
    }
    override fun getFavoriteMovie(): Flow<List<MovieModel>> {
        return localData.getFavoriteMovie().map { DataMapper.mapMovieEntitiesToDomains(it) }
    }




}