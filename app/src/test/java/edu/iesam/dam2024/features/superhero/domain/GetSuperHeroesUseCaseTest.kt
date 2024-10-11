package edu.iesam.dam2024.features.superhero.domain


import edu.iesam.dam2024.features.superhero.data.SuperHeroDataRepository
import edu.iesam.dam2024.features.superhero.data.local.SuperHeroXmlLocalDataSource
import org.junit.jupiter.api.Assertions.*
import edu.iesam.dam2024.features.superhero.data.remote.SuperHeroMockRemoteDataSource
import org.junit.jupiter.api.Test


class GetSuperHeroesUseCaseTest{

    @Test
    fun returnListOfSuperHeroesFromMockWhenLocalIsEmpty(){
        val mockLocalDataSource = mock<SuperHeroXmlLocalDataSource>

        val mockDataSource = SuperHeroDataRepository()

        val useCase = GetSuperHeroesUseCase(mockDataSource)

        val actualSuperHeroes = useCase.invoke()

        val expectedSuperHeroes = mockDataSource.getSuperHeroes()

        assertEquals(expectedSuperHeroes, actualSuperHeroes)
    }

}