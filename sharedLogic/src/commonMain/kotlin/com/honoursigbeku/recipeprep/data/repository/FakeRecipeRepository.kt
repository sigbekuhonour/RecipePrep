package com.honoursigbeku.recipeprep.data.repository

import com.honoursigbeku.recipeprep.domain.model.CategoryName
import com.honoursigbeku.recipeprep.domain.model.RecipeDetail
import com.honoursigbeku.recipeprep.domain.model.RecipeId
import com.honoursigbeku.recipeprep.domain.model.RecipeSummary
import com.honoursigbeku.recipeprep.domain.repository.RecipeRepository


class FakeRecipeRepository : RecipeRepository {

    override suspend fun getRecipesByCategory(category: CategoryName): Result<List<RecipeSummary>> =
        Result.success(
            listOf(
                RecipeSummary(id = RecipeId(value = "1"), title = "Fake recipe", thumbnailUrl = ""),
                RecipeSummary(id = RecipeId(value = "2"), title = "Fake recipe 2", thumbnailUrl = "")
            )
        )

    override suspend fun searchRecipes(query: String): Result<List<RecipeSummary>> =
        Result.success(emptyList())

    override suspend fun getRecipeDetail(id: RecipeId): Result<RecipeDetail> {
        TODO("Not yet implemented — fine for now since the list preview never calls this")
    }
}