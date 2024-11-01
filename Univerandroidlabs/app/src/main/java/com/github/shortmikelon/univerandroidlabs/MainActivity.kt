package com.github.shortmikelon.univerandroidlabs

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.shortmikelon.univerandroidlabs.nav.AppDestination
import com.github.shortmikelon.univerandroidlabs.screens.HomeScreen
import com.github.shortmikelon.univerandroidlabs.screens.animations.AnimationsScreen
import com.github.shortmikelon.univerandroidlabs.screens.lab5.ChangeColorAndAlignment
import com.github.shortmikelon.univerandroidlabs.screens.lab5.FifthLabFirstTask
import com.github.shortmikelon.univerandroidlabs.screens.lab5.FifthLabScreen
import com.github.shortmikelon.univerandroidlabs.screens.lab5.InputActivityResult
import com.github.shortmikelon.univerandroidlabs.screens.lab5.NameInput
import com.github.shortmikelon.univerandroidlabs.screens.rgr2.RGR2Screen
import com.github.shortmikelon.univerandroidlabs.screens.sixthlab.MyGrid
import com.github.shortmikelon.univerandroidlabs.screens.sixthlab.SixthLabDetailsScreen
import com.github.shortmikelon.univerandroidlabs.screens.sixthlab.SixthLabScreen
import com.github.shortmikelon.univerandroidlabs.ui.theme.UniverAndroidLabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Dependencies.init(this.applicationContext)

        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current

            UniverAndroidLabsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(
                        navController = navController,
                        context = context
                    )
                }
            }
        }
    }
}

@Composable
private fun AppNavHost(
    context: Context,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = AppDestination.HOME.name,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(AppDestination.HOME.name) {
            HomeScreen(navController = navController, modifier = modifier)
        }

        composable(AppDestination.ANIMATIONS.name) {
            AnimationsScreen(modifier = modifier)
        }

        composable(AppDestination.FIFTH_LAB.name) {
            FifthLabScreen(navController = navController, modifier = modifier)
        }

        composable(AppDestination.SECOND_RGR.name) {
            RGR2Screen()
        }

        composable(AppDestination.FIFTH_LAB_TASK_1.name) {
            FifthLabFirstTask(context = context)
        }

        composable(AppDestination.FIFTH_LAB_TASK_2.name) {
            NameInput(context)
        }

        composable(AppDestination.FIFTH_LAB_TASK_3.name) {
            InputActivityResult()
        }

        composable(AppDestination.FIFTH_LAB_TASK_4.name) {
            ChangeColorAndAlignment(context)
        }

        composable(AppDestination.SIXTH_LAB_HOME.name) {
            SixthLabScreen(navController = navController)
        }

        composable(
            AppDestination.SIXTH_LAB_DETAILS.name + "/{id}?",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                }
            )
        ) {
            val id = it.arguments?.getLong("id") ?: 0
            SixthLabDetailsScreen(id = id)
        }

        composable("GRID_SCREEN") {
            MyGrid()
        }
    }
}