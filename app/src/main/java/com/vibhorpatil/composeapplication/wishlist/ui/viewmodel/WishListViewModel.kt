package com.vibhorpatil.composeapplication.wishlist.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.vibhorpatil.composeapplication.receipe.UIState
import com.vibhorpatil.composeapplication.wishlist.data.database.WishDatabase
import com.vibhorpatil.composeapplication.wishlist.data.model.Wish
import com.vibhorpatil.composeapplication.wishlist.data.repository.WishRepository
import com.vibhorpatil.composeapplication.wishlist.data.repositoryimpl.WishRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WishListViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow<UIState<List<Wish>>>(UIState.Loading)

    val uiState: StateFlow<UIState<List<Wish>>> = _uiState

    private val wishRepository: WishRepository

    init {
        val wishDao = WishDatabase.getDatabase(application).getWishDao()
        wishRepository = WishRepositoryImpl(wishDao)
        fetchWishList()
    }

    fun addWish(wish: Wish) {
        viewModelScope.launch {
            wishRepository.addAWish(wish)
            fetchWishList()
        }
    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch {
            wishRepository.updateAWish(wish)
            fetchWishList()
        }
    }

    fun deleteWish(wish: Wish) {
        viewModelScope.launch {
            wishRepository.deleteAWish(wish)
            fetchWishList()
        }
    }

    fun fetchWishList() {
        viewModelScope.launch {
            wishRepository.getWishes()
                .catch {
                    _uiState.value = UIState.Error(it.toString())
                }
                .collect { it ->
                    _uiState.value = UIState.Success(it)
                }
        }
    }
}