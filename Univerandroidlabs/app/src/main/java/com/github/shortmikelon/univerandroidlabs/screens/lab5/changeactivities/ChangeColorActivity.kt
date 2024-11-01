package com.github.shortmikelon.univerandroidlabs.screens.lab5.changeactivities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.github.shortmikelon.univerandroidlabs.ui.theme.UniverAndroidLabsTheme

class ChangeColorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UniverAndroidLabsTheme {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Выберите цвет",
                        fontSize = 30.sp
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = {
                            val data = Intent()
                            data.putExtra("Color", 1)
                            setResult(RESULT_OK, data)
                            finish()
                        }) {
                            Text("Голубой")
                        }

                        Button(onClick = {
                            val data = Intent()
                            data.putExtra("Color", 2)
                            setResult(RESULT_OK, data)
                            finish()
                        }) {
                            Text("Темно-синий")
                        }

                        Button(onClick = {
                            val data = Intent()
                            data.putExtra("Color", 3)
                            setResult(RESULT_OK, data)
                            finish()
                        }) {
                            Text("РОЗОВЫЙ МОЙ ЛЮБИМЫЙ)))")
                        }
                    }
                }
            }
        }
    }
}