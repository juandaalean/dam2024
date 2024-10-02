package edu.iesam.dam2024.features.superhero.data

import edu.iesam.dam2024.features.superhero.data.local.SuperHeroXmlLocalDataSource
import edu.iesam.dam2024.features.superhero.data.remote.SuperHeroMockRemoteDataSource
import edu.iesam.dam2024.features.superhero.domain.SuperHero
import edu.iesam.dam2024.features.superhero.domain.SuperHeroRepository

class SuperHeroDataRepository(
    private val local: SuperHeroXmlLocalDataSource,
    private val mockRemoteDataSource: SuperHeroMockRemoteDataSource
) :
    SuperHeroRepository {

    override fun getSuperHeroes(): List<SuperHero>{
        val superHeroFromLocal = local.findAll()
        if (superHeroFromLocal.isEmpty()){
            val superHeroFromRemote = mockRemoteDataSource.getSuperHeroes()
            local.saveAll(superHeroFromRemote)
        }
    }
}