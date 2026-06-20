package com.example.planetapi.data.remote
import com.example.planetapi.data.remote.dto.PlanetDto
import com.example.planetapi.data.remote.dto.PlanetResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
interface DragonBallApi {

    @GET("planets")
    suspend fun getPlanets(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("name") name: String? = null
    ): Response<PlanetResponseDto>


    @GET("planets")
    suspend fun getPlanetsByName(
        @Query("name") name: String
    ): Response<List<PlanetDto>>

    @GET("planets/{id}")
    suspend fun getPlanetDetail(
        @Path("id") id: Int
    ): Response<PlanetDto>
}