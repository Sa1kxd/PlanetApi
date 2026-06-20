package com.example.planetapi.data.remote.datasource
import com.example.planetapi.data.remote.DragonBallApi
import com.example.planetapi.data.remote.dto.PlanetDto

import retrofit2.HttpException
import javax.inject.Inject
class PlanetRemoteDataSource @Inject constructor(
    private val api: DragonBallApi
) {
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Result<List<PlanetDto>> {
        return try {
            if (name != null) {

                val response = api.getPlanetsByName(name)
                if (!response.isSuccessful) {
                    Result.failure(Exception("Error de red ${response.code()}"))
                } else {
                    Result.success(response.body()!!)
                }
            } else {

                val response = api.getPlanets(page, limit, null)
                if (!response.isSuccessful) {
                    Result.failure(Exception("Error de red ${response.code()}"))
                } else {
                    Result.success(response.body()!!.items)
                }
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido: ${e.localizedMessage}"))
        }
    }

    suspend fun getPlanetDetail(id: Int): Result<PlanetDto> {
        return try {
            val response = api.getPlanetDetail(id)
            if (!response.isSuccessful) {
                Result.failure(Exception("Error de red ${response.code()}"))
            } else {
                Result.success(response.body()!!)
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error desconocido: ${e.localizedMessage}"))
        }
    }
}