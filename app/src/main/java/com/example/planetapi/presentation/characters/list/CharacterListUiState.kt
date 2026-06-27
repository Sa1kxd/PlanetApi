package com.example.planetapi.presentation.characters.list
import com.example.planetapi.domain.model.Character

data class CharacterListUiState (
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null,
    val filterName: String = "",
    val filterGender: String = "",
    val filterRace: String = "",
)