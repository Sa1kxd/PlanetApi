package com.example.planetapi.presentation.planets.detail

import com.example.planetapi.domain.model.Planet

data class DetailUiState(
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)