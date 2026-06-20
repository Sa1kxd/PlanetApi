package com.example.planetapi.domain.usecases

import com.example.planetapi.domain.model.Planet
import com.example.planetapi.domain.repository.PlanetRepository
import com.example.planetapi.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(
        page: Int = 1,
        limit: Int = 10,
        name: String? = null
    ): Flow<Resource<List<Planet>>> {
        return repository.getPlanets(page, limit, name)
    }
}