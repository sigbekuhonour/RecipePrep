package com.honoursigbeku.recipeprep.domain.usecase

import com.honoursigbeku.recipeprep.domain.model.RecipeSummary
import com.honoursigbeku.recipeprep.domain.repository.RecipeRepository

class SearchRecipesUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(query: String): Result<List<RecipeSummary>> {
        val trimmed = query.trim()
        if (trimmed.isEmpty()) {
            return Result.success(emptyList())
        }
        return repository.searchRecipes(trimmed)
    }
}