package edu.iesam.dam2024.features.movies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.dam2024.app.domain.ErrorApp
import edu.iesam.dam2024.features.movies.domain.GetMovieUseCase
import edu.iesam.dam2024.features.movies.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState : LiveData<UiState> = _uiState

    fun viewCreated(movieId: String){
        viewModelScope.launch(Dispatchers.IO) {
            val movie = getMovieUseCase.invoke(movieId)
        }
    }

    data class UiState(
        val isLoading: Boolean = false, //saber si esta cargando
        val errorApp: ErrorApp? = null, //saber si hay error
        val movies: Movie? = null //saber si hay peliculas o esa vacio
    )
}