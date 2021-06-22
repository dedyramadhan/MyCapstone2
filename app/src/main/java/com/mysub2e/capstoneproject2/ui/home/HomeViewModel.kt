package com.mysub2e.capstoneproject2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstoneproject2.core.domain.usecase.MoviesUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {

    val movies = moviesUseCase.getAllMovies().asLiveData()

}