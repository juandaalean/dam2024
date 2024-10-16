package edu.iesam.dam2024.features.movies.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import edu.iesam.dam2024.app.extensions.loadUrl
import edu.iesam.dam2024.databinding.FragmentMovieDetailBinding
import edu.iesam.dam2024.features.movies.domain.Movie

class MovieDetailFragment : Fragment() {

    private lateinit var movieFactory: MovieFactory
    private lateinit var viewModel: MovieDetailViewModel

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieFactory = MovieFactory(requireContext())
        viewModel = movieFactory.buildMovieDetailViewModel()
        setupObserver()
        getMovieId()?.let {
            viewModel.viewCreated(it)
        }
    }

    private fun setupObserver() {
        val movieObserver = Observer<MovieDetailViewModel.UiState> { uiState ->
            uiState.movie?.let {
                bindData(it)
            }
            uiState.errorApp?.let {
                //message

            }
            if (uiState.isLoading) {
                //message

            } else {
                //message
            }
        }
        viewModel.uiState.observe(this, movieObserver)
    }

    private fun getMovieId(): String? {
        return "1"
    }

    private fun bindData(movie: Movie) {
        binding.poster.loadUrl(movie.poster)
    }

    companion object {
        val KEY_MOVIE_ID = "key_movie_id"

        fun getIntent(context: Context, movieId: String): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(KEY_MOVIE_ID, movieId)
            return intent
        }
    }

}