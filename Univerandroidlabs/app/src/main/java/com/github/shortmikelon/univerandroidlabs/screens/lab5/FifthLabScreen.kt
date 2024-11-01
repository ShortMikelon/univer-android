package com.github.shortmikelon.univerandroidlabs.screens.lab5

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.shortmikelon.univerandroidlabs.screens.lab5.changeactivities.ChangeAlignmentActivity
import com.github.shortmikelon.univerandroidlabs.screens.lab5.changeactivities.ChangeColorActivity
import com.github.shortmikelon.univerandroidlabs.screens.lab5.inputname.InputNameActivityContract
import com.github.shortmikelon.univerandroidlabs.screens.lab5.showname.ShowNameActivity

@Composable
fun FifthLabScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FifthLabViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = modifier
                .padding(top = 25.dp, start = 10.dp)
        ) {

            Button(onClick = { navController.navigate("FIFTH_LAB_TASK_1") }) {
                Text("1")
            }

            Button(onClick = { navController.navigate("FIFTH_LAB_TASK_2") }) {
                Text("2")
            }

            Button(onClick = { navController.navigate("FIFTH_LAB_TASK_3") }) {
                Text("3")
            }

            Button(onClick = { navController.navigate("FIFTH_LAB_TASK_4") }) {
                Text("4")
            }

        }
    }

}

@Composable
fun FifthLabFirstTask(
    context: Context
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            val intent =
                Intent("com.github.shortmikelon.univerandroidlabs.screens.lab5.showtime")
            context.startActivity(intent)
        }) {
            Text("Показать время")
        }

        Button(
            modifier = Modifier.padding(start = 10.dp),
            onClick = {
                val intent =
                    Intent("com.github.shortmikelon.univerandroidlabs.screens.lab5.showdate")
                context.startActivity(intent)
            }
        ) {
            Text("Показать дату")
        }
    }
}

@Composable
fun NameInput(
    context: Context
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    val onSubmitClicked = {
        val intent = Intent(context, ShowNameActivity::class.java)

        intent.putExtra("first_name", firstName)
        intent.putExtra("last_name", lastName)

        startActivity(
            context,
            intent,
            null
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Введите имя  и фамилия",
            fontSize = 25.sp
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {

            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                modifier = Modifier.width(230.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {

            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                modifier = Modifier.width(230.dp)
            )
        }

        Button(onClick = onSubmitClicked) {
            Text(text = "Отправить")
        }
    }
}

@Composable
fun InputActivityResult() {
    var text by remember { mutableStateOf("") }

    val inputNameActivityLauncher =
        rememberLauncherForActivityResult(contract = InputNameActivityContract()) { result ->
            text = result
        }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = text,
            fontSize = 25.sp,
        )

        Button(onClick = { inputNameActivityLauncher.launch() }) {
            Text("Запустить экран ввода")
        }
    }
}

@Composable
fun ChangeColorAndAlignment(
    context: Context
) {
    var color by remember { mutableStateOf(Color.Black) }
    var arrangement: Arrangement.Horizontal by remember { mutableStateOf(Arrangement.Center) }

    val changeColorActivityLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val i = it.data?.getIntExtra("Color", 1)
                color = when (i) {
                    1 -> Color(0xFF00FFFF)
                    2 -> Color(0xFF000099)
                    3 -> Color(0xFFFF007F)
                    else -> Color(0xFF00FFFF)
                }
            }
        }

    val changeArrangementActivityLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val i = it.data?.getIntExtra("Alignment", 2)
                arrangement = when (i) {
                    1 -> Arrangement.Start
                    2 -> Arrangement.Center
                    3 -> Arrangement.End
                    else -> Arrangement.Center
                }
            }
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(horizontalArrangement = arrangement, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Кара Мухамедкаримов", color = color)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                changeArrangementActivityLauncher.launch(
                    Intent(
                        context,
                        ChangeAlignmentActivity::class.java
                    )
                )
            }) {
                Text("Сменить расположение")
            }

            Button(onClick = {
                changeColorActivityLauncher.launch(
                    Intent(
                        context,
                        ChangeColorActivity::class.java
                    )
                )
            }) {
                Text("Сменить цвет")
            }
        }
    }
}