package com.github.shortmikelon.univerandroidlabs.screens.lab5.inputname

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.shortmikelon.univerandroidlabs.ui.theme.UniverAndroidLabsTheme

class InputNameActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UniverAndroidLabsTheme {

                var firstName by remember { mutableStateOf("") }
                var lastName by remember { mutableStateOf("") }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Введите ваше ФИО")
                    TextField(value = firstName, onValueChange = { firstName = it })

                    TextField(value = lastName, onValueChange = { lastName = it })

                    Button(onClick = { onButtonClicked(firstName, lastName) }) {
                        Text("Вернутся")
                    }
                }
            }
        }
    }

    private fun onButtonClicked(firstName: String, lastName: String) {
        val data = Intent()

        val fullName = "$firstName $lastName"
        data.putExtra(InputNameActivityContract.RESULT_KEY, fullName)

        setResult(RESULT_OK, data)
        finish()
    }
}