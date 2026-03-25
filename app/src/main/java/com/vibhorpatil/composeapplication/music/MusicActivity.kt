package com.vibhorpatil.composeapplication.music

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vibhorpatil.composeapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * 1) Create Screen Sealed Class
 * 2) Create DrawerItem Composable Method
 * 3) Setup NavigationDrawerView with scaffold
 *          1) ModalDrawerSheet
 *
 *
 *
 */

class MusicActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerView()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawerView() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope: CoroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_account),
                            contentDescription = "Account"
                        )
                    },
                    label = { Text("Account") },
                    selected = false,
                    onClick = {}
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_subscribe),
                            contentDescription = "Subscription"
                        )
                    },
                    label = { Text("Subscription") },
                    selected = false,
                    onClick = {}
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_add_account),
                            contentDescription = "Add Account"
                        )
                    },
                    label = { Text("Add Account") },
                    selected = false,
                    onClick = {}
                )
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                com.vibhorpatil.composeapplication.TopAppBar("Hey")
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Show drawer") },
                    icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        ) {

        }
    }

}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if (selected) Color.DarkGray else Color.White
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable {
                onDrawerItemClicked()
            }
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            Modifier.padding(end = 8.dp, top = 8.dp)
        )
        Text(
            text = item.dTitle, style = MaterialTheme.typography.headlineSmall
        )
    }
}