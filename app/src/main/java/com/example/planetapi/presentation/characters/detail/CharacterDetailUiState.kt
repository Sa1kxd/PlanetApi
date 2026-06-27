package com.example.planetapi.presentation.characters.detail

import com.example.planetapi.domain.model.Character

data class CharacterDetailUiState (
    val isLoading: Boolean = false,
    val characters: Character? = null,
    val error: String? = null,
)