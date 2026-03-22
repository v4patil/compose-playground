package com.vibhorpatil.composeapplication.wishlist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vibhorpatil.composeapplication.wishlist.data.model.Wish
import kotlinx.coroutines.flow.Flow


@Dao
interface WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAWish(wishEntity: Wish)

    @Query("Select * from `tbl_wish`")
    fun getAllWishes(): Flow<List<Wish>>

    @Update
    suspend fun updateAWish(wishEntity: Wish)

    @Delete
    suspend fun deleteAWish(wishEntity: Wish)

    @Query("Select * from `tbl_wish` where id=:id")
    fun getAWishById(id: Long): Flow<Wish>

}