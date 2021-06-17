package com.capstoneproject2.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstoneproject2.core.domain.usecase.MoviesUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(moviesUseCase: MoviesUseCase): ViewModel(){
    val favoriteMovies = moviesUseCase.getFavoriteMovie().asLiveData()


}