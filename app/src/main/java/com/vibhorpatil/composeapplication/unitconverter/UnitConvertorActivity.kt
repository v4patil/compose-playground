package com.vibhorpatil.composeapplication.unitconverter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vibhorpatil.composeapplication.TopAppBar
import com.vibhorpatil.composeapplication.ui.theme.ComposeApplicationTheme
import kotlin.math.roundToInt

class UnitConvertorActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeApplicationTheme {
                UnitConvertor()
            }
        }

    }

    fun navigateTo(KClass: Class<*>) {
        startActivity(Intent(this, KClass))
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UnitConvertor() {

    val inputValue = remember { mutableStateOf("") }//.value to access the field and can be final
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var isIExpanded by remember { mutableStateOf(false) }
    var isOExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(0.01) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        color = Color.Red
    )

    fun covertUnits() {
        val inputValueDouble = inputValue.value.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0).roundToInt() / 100.00
        outputValue = result.toString()
    }

    Scaffold(
        topBar = { TopAppBar("Unit Convertor") }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Unit Convertor",
                style =customTextStyle
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputValue.value,
                onValueChange = { inputValue.value = it },
                label = { Text("Enter here") })
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box() {
                    Button(onClick = { isIExpanded = true }) {
                        Text(text = "Convert")
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Drop Down")
                    }
                    DropdownMenu(
                        expanded = isIExpanded,
                        onDismissRequest = { isIExpanded = false }) {
                        DropdownMenuItem(
                            text = { Text("Centimeter") }, onClick = {
                                isIExpanded = false
                                inputUnit = "Centimeter"
                                conversionFactor.value = 0.01
                                covertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Feet") }, onClick = {
                                isIExpanded = false
                                inputUnit = "Feet"
                                conversionFactor.value = 0.0348
                                covertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Millimeter") }, onClick = {
                                isIExpanded = false
                                inputUnit = "Millimeter"
                                conversionFactor.value = 0.01
                                covertUnits()
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box() {
                    Button(onClick = { isOExpanded = true }) {
                        Text(text = "Clear")
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Drop Down")
                        DropdownMenu(
                            expanded = isOExpanded,
                            onDismissRequest = { isOExpanded = false }) {
                            DropdownMenuItem(
                                text = { Text("Centimeter") }, onClick = {}
                            )
                            DropdownMenuItem(
                                text = { Text("Feet") }, onClick = {}
                            )
                            DropdownMenuItem(
                                text = { Text("Millimeter") }, onClick = {}
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Result: $outputValue $outputUnit",
                style = MaterialTheme.typography.headlineMedium
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UnitConvertorPreview() {
    UnitConvertor()
}