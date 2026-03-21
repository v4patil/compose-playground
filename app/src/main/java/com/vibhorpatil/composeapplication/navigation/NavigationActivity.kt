package com.vibhorpatil.composeapplication.navigation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class NavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationView()
        }

    }

    @Composable
    fun NavigationView() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "firstScreen") {
            composable("firstScreen") {
                FirstScreen {name ->
                    navController.navigate("secondScreen/$name")//passing parameter
                }
            }
            composable("secondScreen/{name}") {//passing parameter
                val name = it.arguments?.getString("name") ?: " No Name"
                SecondScreen(name) {
                    navController.navigate("firstScreen")
                }
            }
        }
    }

    @Composable
    fun FirstScreen(navigationToSecondScreen: (String) -> Unit) {
        var name by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "First Screen")
            OutlinedTextField(value = name, onValueChange = { name = it })
            Button(onClick = { navigationToSecondScreen(name) }) {
                Text("Navigate To Second")
            }
        }
    }

    @Composable
    fun SecondScreen(parameterName: String, navigationToFirstActivity: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Second Screen")
            Text(text = "$parameterName Welcome")
            Button(onClick = { navigationToFirstActivity() }) {
                Text("Navigate To First")
            }
        }

    }
}