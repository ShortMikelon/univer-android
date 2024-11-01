package com.github.shortmikelon.univerandroidlabs.screens.lab5.showdate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.github.shortmikelon.univerandroidlabs.ui.theme.UniverAndroidLabsTheme
import java.text.SimpleDateFormat
import java.util.Date

class ShowDateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val format = SimpleDateFormat.getDateInstance()
        val date: String = format.format(Date(System.currentTimeMillis()))

        setContent {
            UniverAndroidLabsTheme {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = date,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}