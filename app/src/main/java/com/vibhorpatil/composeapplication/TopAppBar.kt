package com.vibhorpatil.composeapplication

import androidx.compose.foundation.isSystemInDarkTheme // Add this import
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults    // Add this import
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color             // Add this import
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String) {
    val isDarkTheme = isSystemInDarkTheme()

    // Define your custom colors based on the theme
    val containerColor = if (isDarkTheme) Color.Cyan else Color.Black
    val contentColor = if (isDarkTheme) Color.Black else Color.White

    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        // Use TopAppBarDefaults.topAppBarColors to set colors properly
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = containerColor,
            titleContentColor = contentColor,
            navigationIconContentColor = contentColor,
            actionIconContentColor = contentColor
        ),
        modifier = Modifier.shadow(8.dp)
    )
}