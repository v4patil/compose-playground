package com.vibhorpatil.composeapplication.receipe

sealed interface UIState<out T> {
    data class Success<out T>(val data: T) : UIState<T>
    data class Error(val errorMessage: String) : UIState<Nothing>
    object Loading : UIState<Nothing>
}