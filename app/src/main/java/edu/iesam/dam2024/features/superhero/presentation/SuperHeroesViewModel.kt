package edu.iesam.dam2024.features.superhero.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.dam2024.app.domain.ErrorApp
import edu.iesam.dam2024.features.superhero.domain.GetSuperHeroesUseCase
import edu.iesam.dam2024.features.superhero.domain.SuperHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SuperHeroesViewModel(private val getSuperHeroesUseCase: GetSuperHeroesUseCase)
    : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState : LiveData<UiState> = _uiState

    fun viewCreated() {
        _uiState.value = (UiState(isLoading = true))

        viewModelScope.launch(Dispatchers.IO) {
            val superHeroes = getSuperHeroesUseCase.invoke()
            delay(5000)
            _uiState.postValue(UiState(superheroes = superHeroes))

        }
    }

    data class UiState(
        val isLoading: Boolean = false, //saber si esta cargando
        val errorApp: ErrorApp? = null, //saber si hay error
        val superheroes: List<SuperHero>? = null //saber si hay peliculas o esa vacio
    )

}