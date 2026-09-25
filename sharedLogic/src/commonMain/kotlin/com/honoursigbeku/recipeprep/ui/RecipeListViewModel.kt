package com.honoursigbeku.recipeprep.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honoursigbeku.recipeprep.data.repository.RecipeRepositoryImpl
import com.honoursigbeku.recipeprep.domain.repository.RecipeRepository
import com.honoursigbeku.recipeprep.domain.usecase.GetRecipesByCategoryUseCase
import com.honoursigbeku.recipeprep.domain.usecase.SearchRecipesUseCase
import com.honoursigbeku.recipeprep.ui.models.RecipeListUiState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeListViewModel(
    repository: RecipeRepository = RecipeRepositoryImpl()
) : ViewModel() {

    private val getRecipesByCategoryUseCase = GetRecipesByCategoryUseCase(repository)
    private val searchRecipesUseCase = SearchRecipesUseCase(repository)

    private val _uiState = MutableStateFlow<RecipeListUiState>(RecipeListUiState.Loading)
    val uiState: StateFlow<RecipeListUiState> = _uiState.asStateFlow()

    init {
        loadCategory("Seafood")
    }

    fun loadCategory(category: String) {
        viewModelScope.launch {
            _uiState.update { RecipeListUiState.Loading }
            getRecipesByCategoryUseCase(category)
                .onSuccess { list ->
                    _uiState.update { RecipeListUiState.Content(selectedCategory = category, recipes = list) }
                }
                .onFailure { error ->
                    _uiState.update { RecipeListUiState.Error(error.message ?: "Failed to load category") }
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        if (query.isBlank()) {
            val currentCategory = (_uiState.value as? RecipeListUiState.Content)?.selectedCategory ?: "Seafood"
            loadCategory(currentCategory)
            return
        }

        viewModelScope.launch {
            _uiState.update { RecipeListUiState.Loading }
            searchRecipesUseCase(query)
                .onSuccess { list ->
                    _uiState.update { RecipeListUiState.Content(selectedCategory = "Search: $query", recipes = list) }
                }
                .onFailure { error ->
                    _uiState.update { RecipeListUiState.Error(error.message ?: "Failed to search recipes") }
                }
        }
    }
    fun disposeForIos() {
        viewModelScope.cancel()
    }
}