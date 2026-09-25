package com.honoursigbeku.recipeprep.ui

import com.honoursigbeku.recipeprep.data.remote.RecipeRemoteDataSource
import com.honoursigbeku.recipeprep.data.repository.FakeRecipeRepository
import com.honoursigbeku.recipeprep.data.repository.RecipeRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import com.honoursigbeku.recipeprep.ui.models.RecipeDetailUiState
import com.honoursigbeku.recipeprep.ui.models.RecipeListUiState

object SwiftInjectionHelper {
    fun createRecipeListViewModel(): RecipeListViewModel {
        return RecipeListViewModel(
            repository = RecipeRepositoryImpl(
                remoteDataSource = RecipeRemoteDataSource(),
                ioDispatcher = Dispatchers.IO
            )
        )
    }

    fun createRecipeDetailViewModel(recipeId: String): RecipeDetailViewModel {
        return RecipeDetailViewModel(
            recipeId = recipeId,
            repository = RecipeRepositoryImpl(
                remoteDataSource = RecipeRemoteDataSource(),
                ioDispatcher = Dispatchers.IO
            )
        )
    }

    fun createPreviewRecipeListViewModel(): RecipeListViewModel =
        RecipeListViewModel(repository = FakeRecipeRepository())

    fun dispose(viewModel: RecipeListViewModel) = viewModel.disposeForIos()
    fun dispose(viewModel: RecipeDetailViewModel) = viewModel.disposeForIos()
}
