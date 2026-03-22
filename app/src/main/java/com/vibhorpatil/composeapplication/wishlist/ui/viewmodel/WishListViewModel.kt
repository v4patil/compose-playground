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
        addDummyWish()
        fetchWishList()
    }

    fun addDummyWish() {
        viewModelScope.launch {
            val dummyWish = Wish(
                title = "Dummy Wish",
                desc = "This is a dummy wish created for testing."
            )
            wishRepository.addAWish(dummyWish)
            Log.d("WishListViewModel", "Dummy wish inserted: $dummyWish")

            // Retrieve and show in logcat
            wishRepository.getWishes().collect { wishes ->
                Log.d("WishListViewModel", "Current Wishes in Database:")
                wishes.forEach { wish ->
                    Log.d(
                        "WishListViewModel",
                        "ID: ${wish.id}, Title: ${wish.title}, Description: ${wish.desc}"
                    )
                }
            }
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