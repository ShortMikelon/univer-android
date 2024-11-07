@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.shortmikelon.lab2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.shortmikelon.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    App(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
private fun App(
    modifier: Modifier = Modifier
) {
    var state1 by remember { mutableStateOf(Arrangement.Start) }
    var state2 by remember { mutableStateOf(Arrangement.Start) }
    var state3 by remember { mutableStateOf(Arrangement.Start) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = state1
        ) {
            Button(
                onClick = {
                    state1 = if (state1 == Arrangement.Start) Arrangement.Start
                    else Arrangement.Start
                }
            ) {
                Text(text = "1")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = state2
        ) {
            Button(
                onClick = {
                    state2 = if (state2 == Arrangement.Start) Arrangement.Cen\
                    else Arrangement.Start
                }
            ) {
                Text(text = "2")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = state3
        ) {
            Button(
                onClick = {
                    state3 = if (state3 == Arrangement.Start) Arrangement.End
                    else Arrangement.Start
                }
            ) {
                Text(text = "3")
            }
        }
    }
}
