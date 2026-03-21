package com.vibhorpatil.composeapplication.receipe

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class RecipeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeScreen()
        }
    }
}

@Composable
fun RecipeScreen() {
    val navController = rememberNavController()
    val viewModel: RecipeListViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = RecipeNavigationScreen.RecipeList.route
    ) {
        composable(RecipeNavigationScreen.RecipeList.route) {
            RecipeListRoute(viewModel, { recipeCategory ->
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "category",
                    recipeCategory
                )
                navController.navigate(RecipeNavigationScreen.RecipeDetails.route)
                //As we Cannot send the non-primitive as done in navigation activity
            })
        }

        composable(RecipeNavigationScreen.RecipeDetails.route) {
            val recipeCategory =
                navController.previousBackStackEntry?.savedStateHandle?.get<RecipeCategory>("category")
                    ?: RecipeCategory("1", "Category", "", "CategoryDesc")
            RecipeDetailsScreen(recipeCategory)
        }
    }
}

@Composable
fun RecipeListRoute(viewModel: RecipeListViewModel, onItemClick: (RecipeCategory) -> Unit) {
    val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        is UIState.Loading -> {
            LoadingScreen()
        }

        is UIState.Success -> {
            RecipeListScreen(uiState.data, onItemClick = onItemClick)
        }

        is UIState.Error -> {
            ErrorScreen(uiState.errorMessage)
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(message: String) {
    Log.d("TAG", "ErrorScreen: $message")
    Text(text = message, modifier = Modifier.fillMaxWidth())
}