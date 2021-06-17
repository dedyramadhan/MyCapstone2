package com.capstoneproject2.core.di

import android.content.Context
import com.capstoneproject2.core.domain.repository.IMovieRepository
import com.capstoneproject2.core.domain.usecase.MoviesUseCase
import dagger.BindsInstance
import dagger.Component

@CoreScope
@Component(modules = [RepositoryModule::class])
interface CoreComponent {

    fun provideMoviesUseCase(): MoviesUseCase

    fun provideRepository(): IMovieRepository

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build(): CoreComponent
    }
}