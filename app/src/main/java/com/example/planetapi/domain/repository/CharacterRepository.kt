package com.example.planetapi.domain.repository

import com.example.planetapi.util.Resource
import kotlinx.coroutines.flow.Flow
import com.example.planetapi.domain.model.Character
interface CharacterRepository {
    fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?,
    ): Flow<Resource<List<Character>>>

    fun getCharacterDetail(id: Int): Flow<Resource<Character>>
}