package edu.iesam.dam2024.features.superhero.data

import edu.iesam.dam2024.features.superhero.data.local.SuperHeroXmlLocalDataSource
import edu.iesam.dam2024.features.superhero.data.remote.SuperHeroMockRemoteDataSource
import edu.iesam.dam2024.features.superhero.domain.SuperHero
import edu.iesam.dam2024.features.superhero.domain.SuperHeroRepository

class SuperHeroDataRepository(
    private val mockRemoteDataSource: SuperHeroMockRemoteDataSource,
    private val local: SuperHeroXmlLocalDataSource
) : SuperHeroRepository {


    override fun getSuperHeroes(): List<SuperHero> {
        val superHeroFromLocal = local.findAll()
        if (superHeroFromLocal.isEmpty()) {
            val superHeroFromRemote = mockRemoteDataSource.getSuperHeroes()
            local.saveAll(superHeroFromRemote)
            return superHeroFromRemote
        } else {
            return superHeroFromLocal
        }
    }

    override fun getSuperHero(superheroId: String): SuperHero? {
        val superHeroLocal = local.findById(superheroId)
        if (superHeroLocal == null) {
            mockRemoteDataSource.getSuperHero(superheroId)?.let {
                local.save(it)
                return it
            }
        }
        return superHeroLocal
    }


}