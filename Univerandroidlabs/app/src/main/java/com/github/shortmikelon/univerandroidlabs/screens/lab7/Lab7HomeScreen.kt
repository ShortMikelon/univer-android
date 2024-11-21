package com.github.shortmikelon.univerandroidlabs.screens.lab7

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.shortmikelon.univerandroidlabs.R

@Composable
fun SeventhLabHomeScreen(
    viewModel: SeventhLabHomeViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 48.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    val newState =
                        when (state.imageButtonState) {
                            SeventhLabHomeViewModel.NORMAL -> SeventhLabHomeViewModel.CLICKED
                            SeventhLabHomeViewModel.CLICKED -> SeventhLabHomeViewModel.NORMAL
                            else -> SeventhLabHomeViewModel.FOCUSED
                        }
                    viewModel.onImageButtonStateChanged(newState)
                },
                modifier = Modifier
                    .focusable()
                    .onFocusChanged {
                        if (it.isFocused) viewModel.onImageButtonStateChanged(
                            SeventhLabHomeViewModel.FOCUSED
                        )
                    }
            ) {
                Image(
                    painter = painterResource(
                        id = when (state.imageButtonState) {
                            SeventhLabHomeViewModel.NORMAL -> R.drawable.normal
                            SeventhLabHomeViewModel.FOCUSED -> R.drawable.focused
                            SeventhLabHomeViewModel.CLICKED -> R.drawable.clicked
                            else -> 0
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
            }
        }

        val checkboxText = if (state.chooseMe) "Исключи меня" else "Выбери меня"
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = state.chooseMe,
                onCheckedChange = {
                    viewModel.onChooseMeChanged(it)

                    val toastMessage = if (it) "Ты выбрал меня" else "Ты исключил меня"
                    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                }
            )
            Text(text = checkboxText, fontSize = 20.sp)
        }

        Row {
            val switchText = if (state.isMuted) "Звонок выключен" else "Звонок включен"
            Switch(
                checked = state.isMuted,
                onCheckedChange = {
                    viewModel.onMuteClicked(it)
                    Toast.makeText(context, switchText, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(text = switchText, fontSize = 20.sp)
        }

        SeventhLabHomeViewModel.OPTIONS.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = state.selected == option,
                    onClick = {
                        viewModel.onChangeOption(option)
                        Toast.makeText(context, "$option is selected", Toast.LENGTH_SHORT).show()
                    }
                )
                Text(text = option, fontSize = 20.sp)
            }
        }

        OutlinedTextField(
            value = state.field,
            onValueChange = viewModel::onFieldChanged,
            label = { Text("Введите имя", fontSize = 20.sp) },
            textStyle = TextStyle.Default.copy(fontSize = 20.sp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                Toast.makeText(context, state.field, Toast.LENGTH_SHORT).show()
            }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Button(
            onClick = viewModel::onClearClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Очистить", fontSize = 20.sp)
        }
    }
}

@Preview
@Composable
private fun Lab7HomeScreenPreview() {
    SeventhLabHomeScreen()
}