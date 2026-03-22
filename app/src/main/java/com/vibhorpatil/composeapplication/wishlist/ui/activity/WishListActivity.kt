package com.vibhorpatil.composeapplication.wishlist.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vibhorpatil.composeapplication.TopAppBar
import com.vibhorpatil.composeapplication.receipe.UIState
import com.vibhorpatil.composeapplication.wishlist.data.model.Wish
import com.vibhorpatil.composeapplication.wishlist.ui.viewmodel.WishListViewModel

class WishListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WishListScreen()
        }
    }

    @Composable
    fun WishListScreen() {
        val viewModel: WishListViewModel = viewModel()

        val wishList = viewModel.uiState.collectAsState().value
        Scaffold(
            topBar = { TopAppBar("WishList") }
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
                        WishList(wishList.data)
                    }

                    is UIState.Error -> {
                        WishListError(wishList.errorMessage)
                    }
                }
            }
        }
    }

    @Composable
    fun WishListLoading() {
        CircularProgressIndicator()
    }

    @Composable
    fun WishList(list: List<Wish>) {
        LazyColumn {
            items(list) { wish ->
                Column {
                    Text(text = wish.title)
                    Text(text = wish.desc)
                }
            }
        }
    }

    @Composable
    fun WishListError(errorMsg: String) {
        Text(text = errorMsg)
    }

}