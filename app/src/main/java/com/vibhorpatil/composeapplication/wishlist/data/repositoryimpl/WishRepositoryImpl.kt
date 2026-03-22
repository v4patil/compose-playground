package com.vibhorpatil.composeapplication.wishlist.data.repositoryimpl

import com.vibhorpatil.composeapplication.wishlist.data.dao.WishDao
import com.vibhorpatil.composeapplication.wishlist.data.model.Wish
import com.vibhorpatil.composeapplication.wishlist.data.repository.WishRepository
import kotlinx.coroutines.flow.Flow

class WishRepositoryImpl(private val wishDao: WishDao) : WishRepository {

    override suspend fun addAWish(wish: Wish) {
        wishDao.addAWish(wish)
    }

    override fun getWishes(): Flow<List<Wish>> {
        return wishDao.getAllWishes()
    }

    override fun getAWishById(id: Long): Flow<Wish> {
        return wishDao.getAWishById(id)
    }

    override suspend fun updateAWish(wish: Wish) {
        wishDao.updateAWish(wish)
    }

    override suspend fun deleteAWish(wish: Wish) {
        wishDao.deleteAWish(wish)
    }
}