package com.vibhorpatil.composeapplication

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarMusic(title: String, onClickNavigationIcon: () -> Unit, onClickAction: () -> Unit) {
    val isDarkTheme = isSystemInDarkTheme()

    // Define your custom colors based on the theme
    val containerColor = if (isDarkTheme) Color.Cyan else Color.Black
    val contentColor = if (isDarkTheme) Color.Black else Color.White

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                onClickNavigationIcon()
            }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "HomeIcon"
                )
            }
        },
        title = {
            Text(
                text = title
            )
        },
        actions = {
            IconButton(onClick = { onClickAction() }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "actionMenu")
            }
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
