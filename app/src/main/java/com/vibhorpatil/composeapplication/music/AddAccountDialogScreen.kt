package com.vibhorpatil.composeapplication.music

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun AddAccountDialogScreen(dialogOpen: MutableState<Boolean>) {
    var eMail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = { dialogOpen.value = false },
        title = { Text(text = "Add Account") },
        text = {
            Column(modifier = Modifier
                .wrapContentHeight()
                .padding(top = 10.dp)) {
                TextField(
                    value = eMail,
                    onValueChange = {eMail = it},
                    modifier = Modifier.padding(16.dp),
                    label = { Text("E-mail") })
                TextField(
                    value = password,
                    onValueChange = {password = it},
                    modifier = Modifier.padding(8.dp),
                    label = { Text("Password") })
            }
        },
        dismissButton = {
            TextButton(onClick = { dialogOpen.value = false }) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = { dialogOpen.value = false }) {
                Text("Confirm")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp),
        shape = RoundedCornerShape(5.dp),
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddAccountDialogScreenPreview() {
    AddAccountDialogScreen(remember { mutableStateOf(true) })
}