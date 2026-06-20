package com.example.planetapi.presentation.list

sealed interface ListEvent {
    data class UpdateNameFilter(val name: String) : ListEvent
    data object Search : ListEvent
}