package com.github.shortmikelon.univerandroidlabs.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.github.shortmikelon.univerandroidlabs.nav.AppDestination

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { navController.navigate(AppDestination.ANIMATIONS.name) },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = AppDestination.ANIMATIONS.name, fontSize = 20.sp)
        }

        Button(
            onClick = { navController.navigate(AppDestination.FIFTH_LAB.name) },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = AppDestination.FIFTH_LAB.name, fontSize = 20.sp)
        }

        Button(
            onClick = { navController.navigate(AppDestination.SECOND_RGR.name) },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = "Second rgr", fontSize = 20.sp)
        }

        Button(
            onClick = {
                navController.navigate(AppDestination.SIXTH_LAB_HOME.name)
            },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = "Sixth lab", fontSize = 20.sp)
        }

        Button(
            onClick = { navController.navigate("SEVENTH_LAB_NAVIGATOR_SCREEN") },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = "Seventh lab", fontSize = 20.sp)
        }

        Button(
            onClick = { navController.navigate(AppDestination.THIRD_RGR_HOME.name) },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = "third rgr")
        }
    }
}