package com.capstoneproject2.core.ui

import com.capstoneproject2.core.domain.model.MovieModel

interface MovieAdapterClickListener {
    fun onMovieClickListener(movieData: MovieModel)
}