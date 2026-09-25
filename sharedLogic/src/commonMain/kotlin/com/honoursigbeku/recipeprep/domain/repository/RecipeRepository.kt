package com.honoursigbeku.recipeprep.domain.repository

import com.honoursigbeku.recipeprep.domain.model.CategoryName
import com.honoursigbeku.recipeprep.domain.model.RecipeDetail
import com.honoursigbeku.recipeprep.domain.model.RecipeId
import com.honoursigbeku.recipeprep.domain.model.RecipeSummary

interface RecipeRepository {
    suspend fun getRecipesByCategory(category: CategoryName): Result<List<RecipeSummary>>
    suspend fun searchRecipes(query: String): Result<List<RecipeSummary>>
    suspend fun getRecipeDetail(id: RecipeId): Result<RecipeDetail>
}

