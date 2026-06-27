package com.example.planetapi.data.repository

import com.example.planetapi.data.remote.DragonBallApi
import com.example.planetapi.domain.repository.CharacterRepository
import com.example.planetapi.util.Resource
import com.example.planetapi.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CharacterRepositoryImpl @Inject constructor(
    private val api: DragonBallApi,
) : CharacterRepository {

    override fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?
    ): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getCharacters(page, limit, name, gender, race)
            if (response.isSuccessful) {
                val characters = response.body()?.items?.map { it.toDomain() } ?: emptyList()
                emit(Resource.Success(characters))
            } else {
                emit(Resource.Error(response.message() ?: "Error desconocido"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error de red"))
        }
    }

    override fun getCharacterDetail(id: Int): Flow<Resource<Character>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getCharacterDetail(id)
            if (response.isSuccessful) {
                val character = response.body()?.toDomain()
                if (character != null) {
                    emit(Resource.Success(character))
                } else {
                    emit(Resource.Error("Personaje no encontrado"))
                }
            } else {
                emit(Resource.Error(response.message() ?: "Error desconocido"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error de red"))
        }
    }
}