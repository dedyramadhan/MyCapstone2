package com.mysub2e.capstoneproject2.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstoneproject2.core.data.Resource
import com.capstoneproject2.core.domain.model.MovieModel
import com.capstoneproject2.core.ui.MovieAdapter
import com.capstoneproject2.core.ui.MovieAdapterClickListener
import com.capstoneproject2.core.ui.ViewModelFactory
import com.mysub2e.capstoneproject2.databinding.FragmentHomeBinding
import com.mysub2e.capstoneproject2.ui.BaseApplication
import com.mysub2e.capstoneproject2.ui.detail.DetailMovieActivity
import javax.inject.Inject

class HomeFragment : Fragment(), MovieAdapterClickListener {

    private lateinit var _binding: FragmentHomeBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MovieAdapter(this)

            homeViewModel.movies.observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> {
                            visibleView(_binding.progressBar)
                        }
                        is Resource.Success -> {
                            if (movie.data.isNullOrEmpty()) {
                                visibleView(_binding.noHomeFound.root)
                            } else {
                                movieAdapter.setData(movie.data)
                                visibleView(_binding.rvMovies)
                            }
                        }
                        is Resource.Error -> {
                            _binding.tvViewError.text = movie.message
                            visibleView(_binding.tvViewError)
                        }
                    }
                }
            })

            with(_binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onMovieClickListener(movieData: MovieModel) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.EXTRA_DATA, movieData)
        startActivity(intent)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BaseApplication).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun visibleView(theView: View) {
        _binding.rvMovies.visibility = if (_binding.rvMovies == theView) {
            View.VISIBLE
        } else {
            View.GONE
        }
        _binding.tvViewError.visibility = if (_binding.tvViewError == theView) {
            View.VISIBLE
        } else {
            View.GONE
        }
        _binding.noHomeFound.root.visibility = if (_binding.noHomeFound.root == theView) {
            View.VISIBLE
        } else {
            View.GONE
        }
        _binding.progressBar.visibility = if (_binding.progressBar == theView) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}