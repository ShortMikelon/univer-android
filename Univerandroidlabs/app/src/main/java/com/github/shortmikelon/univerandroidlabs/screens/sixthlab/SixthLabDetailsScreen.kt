package com.github.shortmikelon.univerandroidlabs.screens.sixthlab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SixthLabDetailsScreen(
    id: Long,
    viewModel: SixthLabDetailsViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        if (id != 0L) {
            viewModel.fetchRecord(id)
        }
    }

    val state by viewModel.state.collectAsState()

    Column(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.fillMaxSize().padding(top = 25.dp)) {
        if (state.id != 0L) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text("ID: ${state.id}", fontSize = 30.sp)

                Spacer(modifier = Modifier.width(30.dp))

                Button(onClick = viewModel::update) {
                    Text("Update")
                }

                Button(onClick = viewModel::delete) {
                    Text("Delete")
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("Name", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(30.dp))
            TextField(value = state.name, onValueChange = { viewModel.onChangeField(1, it) })
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text("Email", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(30.dp))
            TextField(value = state.email, onValueChange = { viewModel.onChangeField(2, it) })
        }

        if (state.id == 0L) {
            Button(onClick = viewModel::save) {
                Text("Create")
            }
        }
    }
}

