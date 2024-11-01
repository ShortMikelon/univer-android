package com.github.shortmikelon.univerandroidlabs.screens.lab5.showname

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.github.shortmikelon.univerandroidlabs.ui.theme.UniverAndroidLabsTheme

class ShowNameActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firstName = this.intent.getStringExtra("first_name")
        val lastName = this.intent.getStringExtra("last_name")

        setContent {
            UniverAndroidLabsTheme {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Ваше ФИО: $firstName $lastName",
                        fontSize = 30.sp
                    )
                }
            }
        }
    }

}