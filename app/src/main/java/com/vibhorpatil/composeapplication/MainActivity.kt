package com.vibhorpatil.composeapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vibhorpatil.composeapplication.navigation.NavigationActivity
import com.vibhorpatil.composeapplication.receipe.RecipeActivity
import com.vibhorpatil.composeapplication.shoppinglist.ShoppingListActivity
import com.vibhorpatil.composeapplication.ui.theme.ComposeApplicationTheme
import com.vibhorpatil.composeapplication.unitconverter.UnitConvertorActivity

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeApplicationTheme {
                HomePage({ navigateTo(it) })
            }
        }

    }

    fun navigateTo(KClass: Class<*>) {
        startActivity(Intent(this, KClass))
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navigateTo: (Class<*>) -> Unit) {
    Scaffold(
        topBar = { TopAppBar("Home Page") }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues = paddingValues)
                .padding(0.dp, 8.dp, 0.dp, 0.dp)
        ) {
            Button(onClick = { navigateTo(UnitConvertorActivity::class.java) }) {
                Text(text = "Unit Convertor")
            }
            Button(onClick = { navigateTo(ShoppingListActivity::class.java) }) {
                Text(text = "Shopping List")
            }
            Button(onClick = { navigateTo(NavigationActivity::class.java) }) {
                Text(text = "Navigation")
            }
            Button(onClick = { navigateTo(RecipeActivity::class.java) }) {
                Text(text = "Recipe")
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeApplicationTheme {
        Greeting("Android")
    }
}