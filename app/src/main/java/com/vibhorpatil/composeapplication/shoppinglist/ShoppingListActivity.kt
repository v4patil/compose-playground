package com.vibhorpatil.composeapplication.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vibhorpatil.composeapplication.ui.theme.ComposeApplicationTheme

class ShoppingListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeApplicationTheme {
                ShoppingListUI()
            }
        }
    }

    @Composable
    fun ShoppingListUI() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            val sItems = remember { mutableStateListOf<ShoppingItem>() }
            var isShowDialog by remember { mutableStateOf(false) }


            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { isShowDialog = true },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Add Item")
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(sItems) { item ->
                        val index = sItems.indexOf(item)
                        if (item.isEditing) {
                            ShoppingItemEditor(item, { updatedItem ->
                                sItems[index] = updatedItem
                            })
                        } else {
                            ShoppingListItem(
                                item,
                                {
                                    sItems[index] = item.copy(isEditing = true)
                                },
                                { sItems.remove(item) })
                        }
                    }
                }
            }
            if (isShowDialog) {
                ShoppingItemCreationDialog({ isShowDialog = false }, onItemAdded = { item ->
                    sItems.add(item)
                })
            }
        }
    }


    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun ShoppingListPreview() {
//        ShoppingListUI()
    }
}

