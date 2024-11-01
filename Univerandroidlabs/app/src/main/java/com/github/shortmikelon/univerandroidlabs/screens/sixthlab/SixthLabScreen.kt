package com.github.shortmikelon.univerandroidlabs.screens.sixthlab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.shortmikelon.univerandroidlabs.nav.AppDestination

@Composable
fun SixthLabScreen(
    navController: NavController,
    viewModel: SixthLabViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp)
    ) {
        LazyColumn {
            items(state) { record ->
                RecordCard(record = record, onClick = { navController.navigate(AppDestination.SIXTH_LAB_DETAILS.name + "/${record.id}") })
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { navController.navigate(AppDestination.SIXTH_LAB_DETAILS.name + "/0") }
            ) {
                Text("Create new record")
            }
            Button(onClick = viewModel::refresh) {
                Text("Refresh")
            }
        }

        Button(onClick = { navController.navigate("GRID_SCREEN") }) {
            Text("NExt")
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun RecordCard(
    record: SixthLabEntity,
    onClick: (Long) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onClick(record.id) }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "ID: ${record.id}",
                fontSize = 30.sp,
                modifier = Modifier.fillMaxHeight()
            )

            Spacer(modifier = Modifier.width(50.dp))

            Column(modifier = Modifier.fillMaxHeight()) {
                Text(text = record.name, fontSize = 20.sp)
                Text(text = record.email, fontSize = 10.sp)
            }
        }
    }
}


@Composable
fun MyGrid() {
    val data = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k")
    ArrayTableScreen(data = data)
}

@Composable
private fun ArrayTableScreen(data: Array<String>, columns: Int = 3) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Создаем строки таблицы с количеством столбцов columns
        items(data.asList().chunked(columns)) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { cell ->
                    Text(
                        text = cell,
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center
                    )
                }
                // Заполняем пустые ячейки, если элементов не хватает в последней строке
                repeat(columns - row.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
