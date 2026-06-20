package com.example.planetapi.domain.usecases

import com.example.planetapi.domain.model.Planet
import com.example.planetapi.domain.repository.PlanetRepository
import com.example.planetapi.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPlanetDetailUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    operator fun invoke(id: Int): Flow<Resource<Planet>> {
        return repository.getPlanetDetail(id)
    }
}