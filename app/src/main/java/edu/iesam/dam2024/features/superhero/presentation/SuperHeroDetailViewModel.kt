package edu.iesam.dam2024.features.superhero.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.dam2024.app.domain.ErrorApp
import edu.iesam.dam2024.features.movies.domain.Movie
import edu.iesam.dam2024.features.superhero.domain.GetSuperHeroUseCase
import edu.iesam.dam2024.features.superhero.domain.SuperHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperHeroDetailViewModel(
    private val getSuperHeroUseCase: GetSuperHeroUseCase): ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState : LiveData<UiState> = _uiState

        fun viewCreated(superheroId: String) {
            viewModelScope.launch(Dispatchers.IO) {
                val superhero = getSuperHeroUseCase.invoke(superheroId)
                _uiState.postValue(UiState(superheroes = superhero))

            }
    }

    data class UiState(
        val isLoading: Boolean = false, //saber si esta cargando
        val errorApp: ErrorApp? = null, //saber si hay error
        val superheroes: SuperHero? = null //saber si hay peliculas o esa vacio
    ) {

    }
}