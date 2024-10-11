package edu.iesam.dam2024.features.superhero.domain

import edu.iesam.dam2024.features.superhero.data.SuperHeroDataRepository

class GetSuperHeroesUseCase(private val superHeroRepository: SuperHeroDataRepository) {

    operator fun invoke(): List<SuperHero> {
        return superHeroRepository.getSuperHeroes()
    }
}