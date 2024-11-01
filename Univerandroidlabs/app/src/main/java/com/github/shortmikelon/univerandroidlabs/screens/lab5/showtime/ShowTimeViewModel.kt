package com.github.shortmikelon.univerandroidlabs.screens.lab5.showtime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class ShowTimeViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow("")
    val state get() = _stateFlow.asStateFlow()

    private var job: Job = viewModelScope.launch(Dispatchers.Default) {
        val format = SimpleDateFormat.getTimeInstance()
        while (true) {
            _stateFlow.value = format.format(Date(System.currentTimeMillis()))
            delay(1000L)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}