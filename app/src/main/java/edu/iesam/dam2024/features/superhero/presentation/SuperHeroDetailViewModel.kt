package edu.iesam.dam2024.features.superhero.presentation

import androidx.lifecycle.ViewModel
import edu.iesam.dam2024.features.superhero.domain.GetSuperHeroUseCase
import edu.iesam.dam2024.features.superhero.domain.SuperHero

class SuperHeroDetailViewModel(

    private val getSuperHeroUseCase: GetSuperHeroUseCase): ViewModel() {

        fun viewCreated(superheroId: String): SuperHero?{
            return getSuperHeroUseCase.invoke(superheroId)
    }
}