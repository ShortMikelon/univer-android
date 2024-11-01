package com.github.shortmikelon.univerandroidlabs.screens.rgr2

import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.aay.compose.donutChart.PieChart
import com.aay.compose.donutChart.model.PieChartData

@Composable
fun RGR2Screen() {
    var bananasIsVisible by remember { mutableStateOf(true) }
    var kiwiIsVisible by remember { mutableStateOf(true) }
    var creamIsVisible by remember { mutableStateOf(true) }


    val listPieChartData = mutableListOf<PieChartData>()
    listPieChartData.add(
        PieChartData(
            partName = "Granat",
            data = 33.0,
            color = Color(0xFFFF00FF)
        )
    )
    if (kiwiIsVisible) {
        listPieChartData.add(
            PieChartData(
                partName = "Kiwi",
                data = 33.0,
                color = Color(0xFF009900)
            )
        )
    }
    if (creamIsVisible) {
        listPieChartData.add(
            PieChartData(
                partName = "Cream",
                data = 33.0,
                color = Color(0xFFFFFFCC)
            )
        )
    }
    if (bananasIsVisible) {
        listPieChartData.add(
            PieChartData(
                partName = "Bananas",
                data = 901.0,
                color = Color(0xFFFFFF33)
            )
        )
    }
    

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        PieChart(
            pieChartData = listPieChartData,
            ratioLineColor = Color.LightGray,
            animation = TweenSpec(durationMillis = 500),
            textRatioStyle = TextStyle(color = Color.LightGray),
            modifier = Modifier.size(350.dp)
        )
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { creamIsVisible = !creamIsVisible }) {
                Text(text = if (!creamIsVisible) "Show cream" else "Hide cream")
            }

            Button(onClick = { kiwiIsVisible = !kiwiIsVisible }) {
                Text(text = if (!kiwiIsVisible) "Show kiwi" else "Hide kiwi")
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { bananasIsVisible = !bananasIsVisible }) {
                Text(text = if (!bananasIsVisible) "Show bananas" else "Hide bananas")
            }
        }
    }
}