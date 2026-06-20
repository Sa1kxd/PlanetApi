package com.example.planetapi.presentation.navigation
import kotlinx.serialization.Serializable
sealed class Screen {
    @Serializable
    object List : Screen()

    @Serializable
    data class Detail(val id: Int) : Screen()
}