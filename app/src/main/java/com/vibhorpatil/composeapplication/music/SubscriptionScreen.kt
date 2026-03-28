package com.vibhorpatil.composeapplication.music

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SubscriptionScreen() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Manage Subscription", modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            border = BorderStroke(width = 2.dp, color = Color.Blue)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Musical", modifier = Modifier
                        .padding(16.dp, 8.dp, 4.dp, 1.dp)
                )
                Text(
                    text = "FreeTier", modifier = Modifier
                        .padding(16.dp, 1.dp, 4.dp, 1.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {},
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "See all plans")
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "See All Plans"
                            )
                        }
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .background(Color.Black)
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Get a plan")
                    Text(text = "Get a Plan")
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SubscriptionScreenPreview() {
    SubscriptionScreen()
}