package com.honoursigbeku.recipeprep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.honoursigbeku.recipeprep.ui.RecipeDetailViewModel
import com.honoursigbeku.recipeprep.ui.RecipeListViewModel
import com.honoursigbeku.recipeprep.ui.detail.RecipeDetailScreen
import com.honoursigbeku.recipeprep.ui.list.RecipeListScreen
import com.honoursigbeku.recipeprep.ui.theme.RecipeTheme
import kotlinx.serialization.Serializable

// Navigation Routes
@Serializable
object RecipeListRoute

@Serializable
data class RecipeDetailRoute(val recipeId: String)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            RecipeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = RecipeListRoute
                ) {
                    composable<RecipeListRoute> {
                        val viewModel: RecipeListViewModel = viewModel { RecipeListViewModel() }
                        RecipeListScreen(
                            viewModel = viewModel,
                            onRecipeClick = { id ->
                                navController.navigate(RecipeDetailRoute(recipeId = id))
                            }
                        )
                    }

                    composable<RecipeDetailRoute> { backStackEntry ->
                        val route = backStackEntry.toRoute<RecipeDetailRoute>()
                        val viewModel: RecipeDetailViewModel = viewModel(key = route.recipeId) {
                            RecipeDetailViewModel(recipeId = route.recipeId)
                        }
                        RecipeDetailScreen(
                            viewModel = viewModel,
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
