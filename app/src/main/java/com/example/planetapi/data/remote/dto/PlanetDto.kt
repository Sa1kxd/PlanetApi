package com.example.planetapi.data.remote.dto

import android.media.Image
import com.example.planetapi.domain.model.Planet
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlanetDto(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String
) {
    fun toDomain() = Planet(
        id = id,
        name = name,
        isDestroyed = isDestroyed,
        description = description,
        image = image
    )
}