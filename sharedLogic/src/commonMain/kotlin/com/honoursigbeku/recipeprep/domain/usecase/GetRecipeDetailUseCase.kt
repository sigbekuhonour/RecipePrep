package com.honoursigbeku.recipeprep.domain.usecase

import com.honoursigbeku.recipeprep.domain.model.RecipeDetail
import com.honoursigbeku.recipeprep.domain.model.RecipeId
import com.honoursigbeku.recipeprep.domain.repository.RecipeRepository

class GetRecipeDetailUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: String): Result<RecipeDetail> {
        val recipeId = runCatching { RecipeId(id) }
            .getOrElse { return Result.failure(it) }
        return repository.getRecipeDetail(recipeId)
    }
}