package com.example.planetapi.data.remote.datasource
import android.net.http.HttpException
import androidx.annotation.RequiresExtension
import com.example.planetapi.data.remote.DragonBallApi
import com.example.planetapi.data.remote.dto.CharacterDto
import com.example.planetapi.data.remote.dto.CharacterResponseDto
import javax.inject.Inject
import android.os.Build
class CharacterRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?,
    ): Result<CharacterResponseDto> {
        try {
            val response = api.getCharacters(page, limit, name, gender, race)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getCharacterDetail(id: Int): Result<CharacterDto> {
        try {
            val response = api.getCharacterDetail(id)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }
}