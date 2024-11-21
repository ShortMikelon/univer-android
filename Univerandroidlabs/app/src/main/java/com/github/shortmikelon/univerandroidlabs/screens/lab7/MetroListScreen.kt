package com.github.shortmikelon.univerandroidlabs.screens.lab7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MetroListScreen(
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        listOf(
            "Райымбек Батыр",
            "Жібек Жолы",
            "Алмалы",
            "Абай",
            "Байқоңыр",
            "Ауезов",
            "Алатау",
            "Сайран",
            "Мәскеу",
            "Сарыарқа",
            "Бауыржан Момышұлы"
        ).forEach { metroStation ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        navController.previousBackStackEntry?.savedStateHandle?.set(
                            "metroPickerResult",
                            metroStation
                        )
                        navController.popBackStack()
                    },
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(text = metroStation, fontSize = 20.sp)
                }
            }
        }
    }
}