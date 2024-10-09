package edu.iesam.dam2024.features.movies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import edu.iesam.dam2024.R
import edu.iesam.dam2024.databinding.FragmentMoviesBinding
import edu.iesam.dam2024.features.movies.domain.Movie

class MoviesFragment : Fragment() {

    private lateinit var movieFactory: MovieFactory
    private lateinit var viewModel : MoviesViewModel

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        movieFactory = MovieFactory(requireContext())
        viewModel = movieFactory.buildViewModel()
        viewModel.viewCreated()
    }

    private fun setupObserver() {
        val movieObserver = Observer<MoviesViewModel.UiState>{ uiState ->
            uiState.movies?.let{
                bindData(it)
            }
            uiState.errorApp?.let{
                //message

            }
            if(uiState.isLoading){
                //message

            } else{
                //message
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, movieObserver)
    }

    fun bindData(movies: List<Movie>) {
        binding.movieId1.text = movies[0].id
        binding.movieTitle1.text = movies[0].title
        binding.layout1.setOnClickListener {
        }


        binding.movieId1.text = movies[1].id
        binding.movieTitle1.text = movies[1].title
        binding.layout2.setOnClickListener {
        }

        binding.movieId1.text = movies[2].id
        binding.movieTitle1.text = movies[2].title
        binding.layout3.setOnClickListener {
        }

        binding.movieId1.text = movies[3].id
        binding.movieTitle1.text = movies[3].title
        binding.layout4.setOnClickListener {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*private fun showError(errorApp: ErrorApp) {
      when(error) {
          ErrorApp.InternetErrorApp -> TODO()
          ErrorApp.ServerErrorApp -> TODO()
          ErrorApp.DataErrorApp -> TODO()
          ErrorApp.UnknowErrorApp -> TODO()
      }
  }
  */

    private fun navigateToMovieDetail(movieId: String){
        startActivity(MovieDetailActivity.getIntent(requireContext(), movieId))
    }

}