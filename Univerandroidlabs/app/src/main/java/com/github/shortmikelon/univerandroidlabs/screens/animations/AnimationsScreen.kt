package com.github.shortmikelon.univerandroidlabs.screens.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimationsScreen(
    modifier: Modifier = Modifier
) {
    var colorClicked by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (colorClicked) Color.Red else Color.Blue,
        animationSpec = tween(durationMillis = 1000)
    )

    var visible by remember { mutableStateOf(true) }

    var expanded by remember { mutableStateOf(false) }
    val size by animateDpAsState(
        targetValue = if (expanded) 40.sp.value.dp else 20.sp.value.dp
    )

    var transformIsToggle by remember { mutableStateOf(false) }
    val transform by animateDpAsState(
        targetValue = if (transformIsToggle) 200.dp else 0.dp,
        animationSpec = if (transformIsToggle) spring(dampingRatio = 0.3f) else spring(dampingRatio = 1f)
    )

    var rotationYIsToggle by remember { mutableStateOf(false) }
    val rotationY by animateFloatAsState(
        targetValue = if (rotationYIsToggle) 180f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
    )

    var rotationZIsToggle by remember { mutableStateOf(false) }
    val rotationZ by animateFloatAsState(
        targetValue = if (rotationZIsToggle) 360f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = "Lab 4 Daniel",
                        color = color,
                        fontSize = size.value.sp,
                        modifier = Modifier
                            .padding(top = transform)
                            .graphicsLayer {
                                if (rotationZIsToggle) {
                                    transformOrigin = TransformOrigin(0f, 0.5f)
                                    this.rotationZ = rotationZ
                                } else {
                                    TransformOrigin(0.5f, 0.5f)
                                }
                            }
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
        ) {
            Column {
                Row(modifier = Modifier.fillMaxWidth()) {


                    Button(onClick = { expanded = !expanded }) {
                        Text("Size")
                    }

                    Button(onClick = { visible = !visible }) {
                        Text("visibility")
                    }


                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = { transformIsToggle = !transformIsToggle }) {
                        Text("Transform")
                    }

                    Button(onClick = { colorClicked = !colorClicked }) {
                        Text("Color")
                    }

                    Button(onClick = {
                        rotationZIsToggle = !rotationZIsToggle
                        rotationYIsToggle = false
                    }) {
                        Text("Rotation Z")
                    }
                }
            }
        }
    }
}