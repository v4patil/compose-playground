package com.vibhorpatil.composeapplication.paintapp.data

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vibhorpatil.composeapplication.R
import com.vibhorpatil.composeapplication.ui.theme.ComposeApplicationTheme

class PaintActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_paint_activity)
    }



}