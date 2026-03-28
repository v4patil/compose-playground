package com.vibhorpatil.composeapplication.music

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vibhorpatil.composeapplication.TopAppBarMusic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * 1) Create Screen Sealed Class
 * 2) Create DrawerItem Composable Method
 * 3) Setup NavigationDrawerView with scaffold
 *          1) ModalDrawerSheet
 *          2) Setup TopApp bar
 *          3) Setup Bottom Sheet
 *          4) Setup SubscriptionScreen and AddAccountDialog
 *          5) Set Navigation Route
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawerView() {
    val scope: CoroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    // To get the current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val sheetState = rememberModalBottomSheetState()
    var isShowBottomSheet by remember { mutableStateOf(false) }

    var topAppBarTitle by remember { mutableStateOf("Account") }
    val isShowAddAccountDialog = remember {mutableStateOf(false)}

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Music", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                screensInDrawer.forEach {
                    DrawerItem(currentRoute == it.dRoute.route, it, {
                        scope.launch {
                            drawerState.close()
                        }
                        when (it.dRoute) {
                            MusicScreenNavigation.DrawerScreenNavigation.Account -> {
                                navController.navigate(MusicScreenNavigation.DrawerScreenNavigation.Account.route)
                                topAppBarTitle = "Account"
                            }

                            MusicScreenNavigation.DrawerScreenNavigation.Subscription -> {
                                navController.navigate(MusicScreenNavigation.DrawerScreenNavigation.Subscription.route)
                                topAppBarTitle = "Subscription"
                            }

                            MusicScreenNavigation.DrawerScreenNavigation.AddAccount -> {
                                isShowAddAccountDialog.value = true
                            }
                        }
                    })
                }
            }
        },
        gesturesEnabled = true
    ) {
        if (isShowBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { isShowBottomSheet = false },
                sheetState = sheetState
            ) {
                screensInBottom.forEach {
                    BottomSheetItem(false, it, {})
                }
            }
        }
        Scaffold(
            topBar = {
                TopAppBarMusic(
                    topAppBarTitle, {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                    {
                        isShowBottomSheet = !isShowBottomSheet
                    })
            },
            bottomBar = {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Cyan),
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                    screensInBottom.forEach {
                        BottomMenuItem(false, it, {})
                    }
                }
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
        ) { paddingValues ->

            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)) {
                NavHost(navController = navController, startDestination = MusicScreenNavigation.DrawerScreenNavigation.Account.route) {
                    composable(MusicScreenNavigation.DrawerScreenNavigation.Account.route) {
                        AccountScreen()
                    }
                    composable (MusicScreenNavigation.DrawerScreenNavigation.Subscription.route){
                        SubscriptionScreen()
                    }
                    composable (MusicScreenNavigation.DrawerScreenNavigation.AddAccount.route){
                        isShowAddAccountDialog.value = true
                    }
                }
            }
        }

        if (isShowAddAccountDialog.value) {
            AddAccountDialogScreen(isShowAddAccountDialog)
        }
    }

}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if (selected) Color.LightGray else Color.White
    Row(
        modifier = Modifier
            .fillMaxWidth()
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

@Composable
fun BottomSheetItem(
    selected: Boolean,
    item: Screen.BottomScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if (selected) Color.DarkGray else Color.White
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable {
                onDrawerItemClicked()
            }
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.bTitle,
            Modifier.padding(end = 8.dp, top = 8.dp)
        )
        Text(
            text = item.bTitle, style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun BottomMenuItem(
    selected: Boolean,
    item: Screen.BottomScreen,
    onDrawerItemClicked: () -> Unit
) {
    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.bTitle,
            Modifier
                .wrapContentSize()
                .padding(end = 8.dp, top = 8.dp)
        )
        Text(
            text = item.bTitle, style = MaterialTheme.typography.headlineSmall
        )
    }
}
