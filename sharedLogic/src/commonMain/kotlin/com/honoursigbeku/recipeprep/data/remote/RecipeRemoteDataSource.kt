package com.honoursigbeku.recipeprep.data.remote

import com.honoursigbeku.recipeprep.data.remote.dto.MealDetailResponseDto
import com.honoursigbeku.recipeprep.data.remote.dto.MealSummaryResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RecipeRemoteDataSource(
    private val client: HttpClient = buildRecipeHttpClient()
) {
    private val baseUrl = "https://www.themealdb.com/api/json/v1/1"

    suspend fun fetchByCategory(category: String): MealSummaryResponseDto {
        return client.get("$baseUrl/filter.php") {
            parameter("c", category)
        }.body()
    }

    suspend fun searchByName(query: String): MealSummaryResponseDto {
        return client.get("$baseUrl/search.php") {
            parameter("s", query)
        }.body()
    }

    suspend fun fetchDetailById(id: String): MealDetailResponseDto {
        return client.get("$baseUrl/lookup.php") {
            parameter("i", id)
        }.body()
    }
}