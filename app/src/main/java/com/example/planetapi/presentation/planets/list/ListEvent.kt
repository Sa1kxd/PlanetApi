package com.example.planetapi.presentation.planets.list

sealed interface ListEvent {
    data class UpdateNameFilter(val name: String) : ListEvent
    data object Search : ListEvent
}