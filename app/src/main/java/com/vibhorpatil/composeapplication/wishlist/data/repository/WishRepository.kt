package com.vibhorpatil.composeapplication.wishlist.data.repository

import com.vibhorpatil.composeapplication.wishlist.data.model.Wish
import kotlinx.coroutines.flow.Flow

interface WishRepository {

    suspend fun addAWish(wish: Wish)

    fun getWishes(): Flow<List<Wish>>

    fun getAWishById(id: Long): Flow<Wish>

    suspend fun updateAWish(wish: Wish)

    suspend fun deleteAWish(wish: Wish)
}