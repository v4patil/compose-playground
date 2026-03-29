package com.vibhorpatil.composeapplication.music

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import com.vibhorpatil.composeapplication.R


@Composable
fun BrowseScreen() {
    val browseList = listOf(
        "Hits", "Happy", "Workout", "Running", "TGIF", "Yoga",
        "Hits", "Happy", "Workout", "Running", "TGIF", "Yoga",
        "Hits", "Happy", "Workout", "Running", "TGIF", "Yoga"
    )

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(browseList) {
            HomeItem(it, R.drawable.ic_browse_music)
        }
    }
}