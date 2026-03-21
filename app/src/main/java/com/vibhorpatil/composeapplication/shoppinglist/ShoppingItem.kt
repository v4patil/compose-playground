package com.vibhorpatil.composeapplication.shoppinglist

data class ShoppingItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    var isEditing: Boolean = false
)
