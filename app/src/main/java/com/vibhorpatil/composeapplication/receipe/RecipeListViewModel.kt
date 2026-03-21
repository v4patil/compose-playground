package com.vibhorpatil.composeapplication.receipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<RecipeCategory>>>(UIState.Loading)
    val uiState: StateFlow<UIState<List<RecipeCategory>>> = _uiState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _uiState.value = UIState.Success(response.categories)
            } catch (e: Exception) {
                _uiState.value = UIState.Error(
                    e.message ?: "Something went wrong"
                )
            }
        }
    }
}