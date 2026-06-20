package com.example.planetapi.domain.repository

import com.example.planetapi.domain.model.Planet
import com.example.planetapi.util.Resource
import kotlinx.coroutines.flow.Flow

interface PlanetRepository {

    fun getPlanets(page: Int, limit: Int, name: String?): Flow<Resource<List<Planet>>>

    fun getPlanetDetail(id: Int): Flow<Resource<Planet>>
}