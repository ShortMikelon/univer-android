package com.github.shortmikelon.rgr3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.shortmikelon.rgr3.ui.theme.RGR3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            RGR3Theme {
                AppNavHost(navController = navController)
            }
        }
    }
}

@Composable
private fun AppNavHost(
    navController: NavHostController,
    ) {
    NavHost(
        navController = navController,
        startDestination = "THIRD_RGR_HOME"
    ) {
        composable("THIRD_RGR_HOME") {
            ThirdRGRHomeScreen(navController = navController)
        }

        composable("THIRD_RGR_AUDIO_PLAYER") {
            ThirdRGRAudioPlayerScreen()
        }

        composable("THIRD_RGR_VIDEO_PLAYER") {
            ThirdRGRVideoPlayerScreen()
        }

        composable("THIRD_RGR_PICTURES") {
            ThirdRGRShowPictureScreen()
        }
    }
}