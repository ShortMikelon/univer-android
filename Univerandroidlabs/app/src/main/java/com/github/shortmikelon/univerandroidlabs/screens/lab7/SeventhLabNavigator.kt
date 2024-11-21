package com.github.shortmikelon.univerandroidlabs.screens.lab7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SeventhLabNavigatorScreen(navController: NavController) {
    val metroPickerResult = navController
        .currentBackStackEntry
        ?.savedStateHandle
        ?.get("metroPickerResult") ?: ""

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = { navController.navigate("SEVENTH_LAB_HOME_SCREEN") },
            modifier = Modifier.width(300.dp)
        ) {
            Text(text = "First Task", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = { navController.navigate("SEVENTH_LAB_METRO_PICKER") },
            modifier = Modifier.width(300.dp)
        ) {
            val buttonText = if (metroPickerResult.isEmpty()) "Choose metro station" else "$metroPickerResult is selected"
            Text(text = buttonText, fontSize = 20.sp, textAlign = TextAlign.Center)
        }
    }
}