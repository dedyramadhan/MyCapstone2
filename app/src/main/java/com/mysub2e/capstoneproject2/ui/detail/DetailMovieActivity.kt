package com.mysub2e.capstoneproject2.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.capstoneproject2.core.data.Resource
import com.capstoneproject2.core.domain.model.MovieModel
import com.capstoneproject2.core.ui.ViewModelFactory
import com.mysub2e.capstoneproject2.R
import com.mysub2e.capstoneproject2.databinding.ActivityDetailMovieBinding
import com.mysub2e.capstoneproject2.ui.BaseApplication
import javax.inject.Inject

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    companion object {
        const val EXTRA_DATA = "extra_movie_data"
    }

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory

    private val detailMoviesViewModel: DetailMovieViewModel by viewModels {
        viewmodelFactory
    }


    private fun setUpFab(movieData: MovieModel) {
        var statusFavorite = movieData.movieIsFavorite
        if (statusFavorite) {
            binding.fabDetailMovie.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.fabDetailMovie.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        }

        binding.fabDetailMovie.setOnClickListener {
            statusFavorite = !statusFavorite
            detailMoviesViewModel.setFavoriteMovie(movieData, statusFavorite)

            if (statusFavorite) {
                binding.fabDetailMovie.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favorite
                    )
                )
            } else {
                binding.fabDetailMovie.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_favorite
                    )
                )
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val movieData = intent.getParcelableExtra<MovieModel>(EXTRA_DATA)

        movieData?.let {
            detailMoviesViewModel.setMovieId(it.movieId)

            detailMoviesViewModel.detailMovie.observe(this, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> {
                            binding.llContentDetailMovie.visibility = View.GONE
                            binding.viewErrorDetailMovie.visibility = View.GONE
                            binding.progressBarDetailMovie.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBarDetailMovie.visibility = View.GONE
                            if (!movies.data.isNullOrEmpty()) {
                                val movie = movies.data?.get(0) as MovieModel
                                binding.tvTitleDetailMovie.text = movie.movieTitle
                                binding.tvDetailDescription.text = movie.movieDescription
                                Glide.with(this)
                                    .load(
                                        resources.getString(
                                            R.string.image_url_string_original,
                                            movie.movieLandscapeImage
                                        )
                                    )
                                    .into(binding.ivDetailImage)

                                setUpFab(movie)
                                binding.viewErrorDetailMovie.visibility = View.GONE
                                binding.llContentDetailMovie.visibility = View.VISIBLE
                            } else {
                                binding.viewErrorDetailMovie.text =
                                    resources.getString(R.string.empty_text)
                                binding.llContentDetailMovie.visibility = View.GONE
                                binding.viewErrorDetailMovie.visibility = View.VISIBLE
                            }
                        }
                        is Resource.Error -> {
                            binding.viewErrorDetailMovie.text =
                                resources.getString(R.string.oops_something_went_wrong)
                            binding.llContentDetailMovie.visibility = View.GONE
                            binding.progressBarDetailMovie.visibility = View.GONE
                            binding.viewErrorDetailMovie.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
    }
}