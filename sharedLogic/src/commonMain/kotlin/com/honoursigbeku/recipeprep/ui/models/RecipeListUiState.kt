package com.honoursigbeku.recipeprep.ui.models
import com.honoursigbeku.recipeprep.domain.model.RecipeSummary

sealed interface RecipeListUiState {
    data object Loading : RecipeListUiState
    data class Content(
        val selectedCategory: String,
        val recipes: List<RecipeSummary>,
        val isRefreshing: Boolean = false
    ) : RecipeListUiState
    data class Error(val message: String) : RecipeListUiState
}