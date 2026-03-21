package com.vibhorpatil.composeapplication.receipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeDetailsScreen(category: RecipeCategory?) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Category", color = Color.Black, textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )

        Image(
            painter = rememberAsyncImagePainter(category?.strCategoryThumb),
            contentDescription = "Category Image",
            modifier = Modifier.fillMaxWidth().wrapContentHeight().aspectRatio(1f)
        )

        Text(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            text = category?.strCategoryDescription ?: "Abc",
            textAlign = TextAlign.Justify,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.bodyMedium
        )


    }

}