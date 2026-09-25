package com.honoursigbeku.recipeprep.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honoursigbeku.recipeprep.data.repository.RecipeRepositoryImpl
import com.honoursigbeku.recipeprep.domain.repository.RecipeRepository
import com.honoursigbeku.recipeprep.domain.usecase.GetRecipeDetailUseCase
import com.honoursigbeku.recipeprep.ui.models.RecipeDetailUiState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val recipeId: String,
    repository: RecipeRepository = RecipeRepositoryImpl()
) : ViewModel() {

    private val getRecipeDetailUseCase = GetRecipeDetailUseCase(repository)

    private val _uiState = MutableStateFlow<RecipeDetailUiState>(RecipeDetailUiState.Loading)
    val uiState: StateFlow<RecipeDetailUiState> = _uiState.asStateFlow()

    init {
        loadDetails()
    }

    fun loadDetails() {
        viewModelScope.launch {
            _uiState.update { RecipeDetailUiState.Loading }
            getRecipeDetailUseCase(recipeId)
                .onSuccess { detail ->
                    _uiState.update { RecipeDetailUiState.Content(recipe = detail) }
                }
                .onFailure { error ->
                    _uiState.update { RecipeDetailUiState.Error(error.message ?: "Failed to load recipe details") }
                }
        }
    }
    fun disposeForIos() {
        viewModelScope.cancel()
    }
}