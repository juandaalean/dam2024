package edu.iesam.dam2024.features.superhero.presentation

import android.content.Context
import edu.iesam.dam2024.features.superhero.data.SuperHeroDataRepository
import edu.iesam.dam2024.features.superhero.data.local.SuperHeroXmlLocalDataSource
import edu.iesam.dam2024.features.superhero.data.remote.SuperHeroMockRemoteDataSource
import edu.iesam.dam2024.features.superhero.domain.GetSuperHeroUseCase
import edu.iesam.dam2024.features.superhero.domain.GetSuperHeroesUseCase

class SuperHeroFactory(private val context: Context){

    private val superHeroMockRemote = SuperHeroMockRemoteDataSource()
    private val superHeroLocal = SuperHeroXmlLocalDataSource(context)
    private val superHeroDataRepository = SuperHeroDataRepository(superHeroMockRemote, superHeroLocal)
    private val getSuperHeroesUseCase = GetSuperHeroesUseCase(superHeroDataRepository)
    private val getSuperHeroUseCase = GetSuperHeroUseCase(superHeroDataRepository)


    fun buildViewModel(): SuperHeroesViewModel {
        return SuperHeroesViewModel(getSuperHeroesUseCase)
    }


}