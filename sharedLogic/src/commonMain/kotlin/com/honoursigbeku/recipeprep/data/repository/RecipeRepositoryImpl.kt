package com.honoursigbeku.recipeprep.data.repository

import com.honoursigbeku.recipeprep.data.remote.RecipeRemoteDataSource
import com.honoursigbeku.recipeprep.data.remote.dto.toDomain
import com.honoursigbeku.recipeprep.data.remote.dto.toRecipeDetailDomain
import com.honoursigbeku.recipeprep.domain.model.CategoryName
import com.honoursigbeku.recipeprep.domain.model.RecipeDetail
import com.honoursigbeku.recipeprep.domain.model.RecipeId
import com.honoursigbeku.recipeprep.domain.model.RecipeSummary
import com.honoursigbeku.recipeprep.domain.repository.RecipeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(
    private val remoteDataSource: RecipeRemoteDataSource = RecipeRemoteDataSource(),
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RecipeRepository {

    override suspend fun getRecipesByCategory(category: CategoryName): Result<List<RecipeSummary>> =
        withContext(ioDispatcher) {
            runCatching {
                val response = remoteDataSource.fetchByCategory(category.value)
                response.meals?.map { it.toDomain() } ?: emptyList()
            }
        }

    override suspend fun searchRecipes(query: String): Result<List<RecipeSummary>> =
        withContext(ioDispatcher) {
            runCatching {
                val response = remoteDataSource.searchByName(query)
                response.meals?.map { it.toDomain() } ?: emptyList()
            }
        }

    override suspend fun getRecipeDetail(id: RecipeId): Result<RecipeDetail> =
        withContext(ioDispatcher) {
            runCatching {
                val response = remoteDataSource.fetchDetailById(id.value)
                val firstMealJson = response.meals?.firstOrNull()
                    ?: throw NoSuchElementException("Recipe not found with id: ${id.value}")
                firstMealJson.toRecipeDetailDomain()
            }
        }
}