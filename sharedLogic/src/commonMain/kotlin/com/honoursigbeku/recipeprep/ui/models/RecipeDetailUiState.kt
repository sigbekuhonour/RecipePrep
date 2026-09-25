package com.honoursigbeku.recipeprep.ui.models

import com.honoursigbeku.recipeprep.domain.model.RecipeDetail

sealed interface RecipeDetailUiState {
    data object Loading : RecipeDetailUiState
    data class Content(val recipe: RecipeDetail) : RecipeDetailUiState
    data class Error(val message: String) : RecipeDetailUiState
}