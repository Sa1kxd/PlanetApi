package com.example.planetapi.presentation.planets.list

import com.example.planetapi.domain.model.Planet

data class ListUiState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val error: String? = null,
    val filterName: String = ""
)
