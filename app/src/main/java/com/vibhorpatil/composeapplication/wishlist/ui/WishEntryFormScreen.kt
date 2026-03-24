package com.vibhorpatil.composeapplication.wishlist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vibhorpatil.composeapplication.TopAppBar
import com.vibhorpatil.composeapplication.wishlist.data.model.Wish

@Composable
fun WishEntryFormScreen(wish: Wish?, onSaveUpdate: (Wish) -> Unit) {
    var id: Long by remember { mutableLongStateOf(0L) }
    var title: String by remember { mutableStateOf("") }
    var desc: String by remember { mutableStateOf("") }
    if (wish != null) {
        id = wish.id
        title = wish.title
        desc = wish.desc
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar("Add Wish") },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        onSaveUpdate(Wish(id, title, desc))
                    }
                ) {
                    Text(text = "Save Wish")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Enter Wish") },
                modifier = Modifier
                    .fillMaxWidth().padding(16.dp)
            )
            OutlinedTextField(
                value = desc,
                onValueChange = { desc = it },
                label = { Text(text = "Enter Description") },
                modifier = Modifier
                    .fillMaxWidth().padding(16.dp)
            )
        }
    }
}