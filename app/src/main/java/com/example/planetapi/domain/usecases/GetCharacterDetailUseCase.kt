package com.example.planetapi.domain.usecases

import com.example.planetapi.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersDetailUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    operator fun invoke(id: Int) = repository.getCharacterDetail(id)
}