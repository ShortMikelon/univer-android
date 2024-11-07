package com.github.shortmikelon.lab3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.shortmikelon.lab3.ui.theme.Lab3Theme

private const val HOME_SCREEN = 1
private const val RADIO_BUTTON_SCREEN = 2
private const val SCROLL_SCREEN = 3
private const val CALCULATOR_SCREEN = 4

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab3Theme {
                App()
            }
        }
    }
}

@Composable
private fun App() {
    var screenIndex by remember { mutableIntStateOf(1) }

    var selectedRadioButton by remember { mutableIntStateOf(3) }

    Scaffold {
        Box(
            modifier = Modifier.padding(it)
        ) {
            when (screenIndex) {
                HOME_SCREEN -> HomeScreen(
                    moveToRadioButtonScreen = {
                        screenIndex = RADIO_BUTTON_SCREEN
                    },
                    moveToCalculatorScreen = {
                        screenIndex = CALCULATOR_SCREEN
                    },
                    moveToScrollScreen = {
                        screenIndex = SCROLL_SCREEN
                    }
                )

                RADIO_BUTTON_SCREEN -> CreateButtonScreen(
                    onBackNavigate = { screenIndex = HOME_SCREEN },
                    selectedRadioButton = selectedRadioButton,
                    onRadioButtonClicked = { selectedRadioButton = it }
                )

                SCROLL_SCREEN -> ScrollScreen(onBackNavigate = { screenIndex = HOME_SCREEN })

                CALCULATOR_SCREEN -> CalculatorScreen(onBackNavigate = {
                    screenIndex = HOME_SCREEN
                })
            }
        }
    }
}

@Composable
private fun HomeScreen(
    moveToRadioButtonScreen: () -> Unit,
    moveToScrollScreen: () -> Unit = { },
    moveToCalculatorScreen: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            shape = RectangleShape,
            modifier = Modifier.width(200.dp),
            onClick = moveToRadioButtonScreen
        ) {
            Text(text = "Radio Button Screen")
        }

        Button(
            shape = RectangleShape,
            modifier = Modifier.width(200.dp),
            onClick = moveToScrollScreen
        ) {
            Text(text = "Scroll Screen")
        }

        Button(
            shape = RectangleShape,
            modifier = Modifier.width(200.dp),
            onClick = moveToCalculatorScreen
        ) {
            Text(text = "Calculation screen")
        }
    }
}

@Composable
private fun CreateButtonScreen(
    onBackNavigate: () -> Unit,
    selectedRadioButton: Int,
    onRadioButtonClicked: (Int) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    var buttons by remember { mutableStateOf(mapOf<String, Int>()) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            IconButton(onClick = { onBackNavigate() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Text(
                text = "Create button screen",
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(top = 15.dp, end = 15.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                RadioButton(
                    selected = selectedRadioButton == 1,
                    onClick = { onRadioButtonClicked(1) }
                )
                Text(
                    text = "left",
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

            Row {
                RadioButton(
                    selected = selectedRadioButton == 2,
                    onClick = { onRadioButtonClicked(2) }
                )
                Text(
                    text = "center",
                    modifier = Modifier.padding(top = 12.dp)
                )
            }

            Row {
                RadioButton(
                    selected = selectedRadioButton == 3,
                    onClick = { onRadioButtonClicked(3) }
                )
                Text(text = "right", modifier = Modifier.padding(top = 12.dp))
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.size(width = 160.dp, height = 50.dp)
            )

            Button(
                modifier = Modifier.size(width = 90.dp, height = 50.dp),
                onClick = {
                    val copy = buttons.toMutableMap()
                    copy[text] = selectedRadioButton
                    text = ""
                    buttons = copy
                    Log.d("flkdsjafl", copy.toString())
                }
            ) {
                Text(text = "Create")
            }

            Button(
                modifier = Modifier.size(width = 90.dp, height = 50.dp),
                onClick = {
                    val copy = buttons.toMutableMap()
                    copy.clear()
                    buttons = copy
                    Log.d("flkdsjafl", copy.toString())
                }
            ) {
                Text(text = "Clear")
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            buttons.forEach {
                Row(
                    horizontalArrangement = getArrangement(it.value),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = {}) {
                        Text(it.key)
                    }
                }
            }
        }


    }
}

@Composable
private fun getArrangement(a: Int): Arrangement.Horizontal {
    return when (a) {
        1 -> Arrangement.Start
        2 -> Arrangement.Center
        3 -> Arrangement.End
        else -> throw IllegalArgumentException()
    }
}

@Composable
private fun ScrollScreen(
    onBackNavigate: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            IconButton(onClick = { onBackNavigate() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Text(
                text = "Resize button screen",
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(top = 15.dp, end = 15.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var sliderPosition by remember { mutableFloatStateOf(0.5f) }

            Slider(
                value = sliderPosition,
                onValueChange = {
                    Log.d("TAG", it.toString())
                    if (it in 0.00000001f..0.999999f) {
                        sliderPosition = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = SliderDefaults.colors(
                    thumbColor = Color.Gray,
                    activeTrackColor = Color.Yellow,
                    inactiveTrackColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier.weight(sliderPosition).height(50.dp)
                ) {
                    Text(text = "Button1", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))


                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f - sliderPosition)
                        .height(50.dp)
                ) {
                    Text(text = "Button2", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
private fun CalculatorScreen(
    onBackNavigate: () -> Unit
) {
    var rightText by remember { mutableStateOf("") }
    var leftText by remember { mutableStateOf("") }

    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            IconButton(onClick = { onBackNavigate() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

            Text(
                text = "Calculator screen",
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(top = 15.dp, end = 15.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = leftText,
                onValueChange = { leftText = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(100.dp)
            )
            TextField(
                value = rightText,
                onValueChange = { rightText = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(100.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                shape = RectangleShape,
                onClick = {
                    if (leftText.isEmpty()) leftText = "0.0"
                    if (rightText.isEmpty()) rightText = "0.0"
                    result =
                        "$leftText + $rightText = ${leftText.toDouble() + rightText.toDouble()}"
                }
            ) {
                Text("+")
            }

            Button(
                shape = RectangleShape,
                onClick = {
                    if (leftText.isEmpty()) leftText = "0.0"
                    if (rightText.isEmpty()) rightText = "0.0"
                    result =
                        "$leftText - $rightText = ${leftText.toDouble() - rightText.toDouble()}"
                }) {
                Text("-")
            }

            Button(
                shape = RectangleShape,
                onClick = {
                    if (leftText.isEmpty()) leftText = "0.0"
                    if (rightText.isEmpty()) rightText = "0.0"
                    result =
                        "$leftText * $rightText = ${leftText.toDouble() * rightText.toDouble()}"
                }) {
                Text("*")
            }

            Button(
                shape = RectangleShape,
                onClick = {
                    if (leftText.isEmpty()) leftText = "0.0"
                    if (rightText.isEmpty()) rightText = "0.0"
                    val t = String.format("%.4f", leftText.toDouble() / rightText.toDouble())
                    result =
                        "$leftText / $rightText = $t"
                }) {
                Text("/")
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(result)
        }
    }
}