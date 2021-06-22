package com.mysub2e.capstoneproject2.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstoneproject2.core.domain.model.MovieModel
import com.capstoneproject2.core.domain.usecase.MoviesUseCase
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) :
    ViewModel() {
    private val movieId = MutableLiveData<Int>()

    fun setMovieId(movieId: Int) {
        this.movieId.value = movieId
    }

    fun setFavoriteMovie(movie: MovieModel, newStatus: Boolean) =
        moviesUseCase.setFavoriteMovie(movie, newStatus)

    val detailMovie = Transformations.switchMap(movieId) { movieIds ->
        moviesUseCase.getMovieDetailByMovieId(movieIds).asLiveData()
    }

}