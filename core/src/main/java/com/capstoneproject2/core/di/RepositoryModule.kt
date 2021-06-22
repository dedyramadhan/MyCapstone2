package com.capstoneproject2.core.di

import com.capstoneproject2.core.data.repository.MovieRepository
import com.capstoneproject2.core.domain.interactor.MovieInteractor
import com.capstoneproject2.core.domain.repository.IMovieRepository
import com.capstoneproject2.core.domain.usecase.MoviesUseCase
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MoviesUseCase

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository

}