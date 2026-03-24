package com.vibhorpatil.composeapplication.wishlist.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vibhorpatil.composeapplication.TopAppBar
import com.vibhorpatil.composeapplication.receipe.UIState
import com.vibhorpatil.composeapplication.wishlist.data.model.Wish
import com.vibhorpatil.composeapplication.wishlist.ui.WishEntryFormScreen
import com.vibhorpatil.composeapplication.wishlist.ui.viewmodel.WishListViewModel

class WishListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: WishListViewModel = viewModel()
            var wish: Wish? = null

            NavHost(
                navController = navController,
                startDestination = "S1"
            ) {
                composable("S1") {
                    WishListScreen(
                        viewModel, { navController.navigate("S2") },
                        {
                            wish = it
                            navController.navigate("S2")
                        })
                }

                composable("S2") {
                    WishEntryFormScreen(wish, { wish ->
                        if (wish.id == 0L) {
                            viewModel.addWish(wish)
                        } else {
                            viewModel.updateWish(wish)
                        }
                        navController.navigate("S1")
                    })
                }
            }
        }
    }

    @Composable
    fun WishListScreen(
        viewModel: WishListViewModel,
        onClickAddNewWish: () -> Unit,
        onClickEdit: (Wish) -> Unit
    ) {
        val wishList = viewModel.uiState.collectAsState().value
        val showDialog = remember { mutableStateOf(false) }
        val selectedWish = remember { mutableStateOf<Wish?>(null) }
        Scaffold(
            topBar = { TopAppBar("WishList") },
            floatingActionButton = {
                Button(onClick = { onClickAddNewWish() }) {
                    Image(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Add New Wish"
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
                when (wishList) {
                    is UIState.Loading -> {
                        WishListLoading()
                    }

                    is UIState.Success -> {
                        WishList(wishList.data, { onClickEdit(it) }, { wish ->
                            selectedWish.value = wish
                            showDialog.value = true
                        })
                    }

                    is UIState.Error -> {
                        WishListError(wishList.errorMessage)
                    }
                }

                if (showDialog.value) {
                    DeleteAlertDialog(
                        onConfirm = {
                            selectedWish.value?.let { viewModel.deleteWish(it) }
                            showDialog.value = false
                        },
                        onDismiss = {
                            showDialog.value = false
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun WishListLoading() {
        CircularProgressIndicator()
    }

    @Composable
    fun WishList(list: List<Wish>, onClickEdit: (Wish) -> Unit, onclickDelete: (Wish) -> Unit) {
        LazyColumn {
            items(list) { wish ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Text(text = wish.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(text = wish.desc, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                    Row {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Wish",
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable(onClick = { onClickEdit(wish) })
                        )
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Wish",
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable(onClick = { onclickDelete(wish) })
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun DeleteAlertDialog(
        onConfirm: () -> Unit,
        onDismiss: () -> Unit
    ) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Confirm Delete") },
            text = { Text("Are you sure you want to delete?") },
            confirmButton = {
                Text(
                    text = "YES",
                    modifier = Modifier.clickable { onConfirm() }
                )
            },
            dismissButton = {
                Text(
                    text = "NO",
                    modifier = Modifier.clickable { onDismiss() }
                )
            }
        )
    }


    @Composable
    fun WishListError(errorMsg: String) {
        Text(text = errorMsg)
    }

}