package com.vibhorpatil.composeapplication.receipe

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeCategory(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
) : Parcelable

data class CategoriesResponse(val categories: List<RecipeCategory>)
