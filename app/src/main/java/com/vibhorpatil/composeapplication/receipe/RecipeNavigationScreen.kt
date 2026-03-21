package com.vibhorpatil.composeapplication.receipe

sealed class RecipeNavigationScreen(val route: String) {
    object RecipeList : RecipeNavigationScreen("recipeList")
    object RecipeDetails : RecipeNavigationScreen("recipeDetails")
}