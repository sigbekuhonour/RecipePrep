package com.honoursigbeku.recipeprep.domain.usecase

import com.honoursigbeku.recipeprep.domain.model.CategoryName
import com.honoursigbeku.recipeprep.domain.model.RecipeSummary
import com.honoursigbeku.recipeprep.domain.repository.RecipeRepository

class GetRecipesByCategoryUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(categoryName: String): Result<List<RecipeSummary>> {
        val category = runCatching { CategoryName(categoryName) }
            .getOrElse { return Result.failure(it) }
        return repository.getRecipesByCategory(category)
    }
}
