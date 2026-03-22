package com.vibhorpatil.composeapplication.wishlist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vibhorpatil.composeapplication.wishlist.data.dao.WishDao
import com.vibhorpatil.composeapplication.wishlist.data.model.Wish

@Database(version = 1, entities = [Wish::class], exportSchema = false)
abstract class WishDatabase : RoomDatabase() {

    abstract fun getWishDao(): WishDao

    companion object {
        @Volatile
        private var INSTANCE: WishDatabase? = null

        fun getDatabase(context: Context): WishDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WishDatabase::class.java,
                    "WishDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}