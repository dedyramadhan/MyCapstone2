package com.capstoneproject2.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstoneproject2.core.domain.model.MovieModel
import com.capstoneproject2.core.ui.MovieAdapter
import com.capstoneproject2.core.ui.MovieAdapterClickListener
import com.capstoneproject2.favorite.databinding.FragmentFavoriteBinding
import com.mysub2e.capstoneproject2.ui.BaseApplication
import com.mysub2e.capstoneproject2.ui.detail.DetailMovieActivity
import javax.inject.Inject

class FavoriteFragment : Fragment(), MovieAdapterClickListener {

    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }
    private lateinit var _binding: FragmentFavoriteBinding

    @Inject
    lateinit var factory: ViewModelFavoriteFactory

    override fun onMovieClickListener(movieData: MovieModel) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.EXTRA_DATA, movieData)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val appComponent = (requireActivity().application as BaseApplication).appComponent
        DaggerFavoriteComponent.builder().appComponent(appComponent).build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieAdapter = MovieAdapter(this)

        favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner, { favoriteMovies ->
            movieAdapter.setData(favoriteMovies)
            if (favoriteMovies.isEmpty()) {
                _binding.rvMoviesFavorite.visibility = View.GONE
                _binding.noFavoriteFound.root.visibility = View.VISIBLE
            } else {
                _binding.rvMoviesFavorite.visibility = View.VISIBLE
                _binding.noFavoriteFound.root.visibility = View.GONE
            }
        })

        with(_binding.rvMoviesFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}