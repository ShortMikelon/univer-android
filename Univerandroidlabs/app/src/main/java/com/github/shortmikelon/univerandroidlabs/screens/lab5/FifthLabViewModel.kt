package com.github.shortmikelon.univerandroidlabs.screens.lab5

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FifthLabViewModel : ViewModel() {

    private val _state = MutableStateFlow(State.INITIAL_STATE)
    val state get() = _state.asStateFlow()

    fun onFieldChange(field: Int, newValue: String) {
        when (field) {
            1 -> _state.value = _state.value.copy(firstName = newValue)
            2 -> _state.value = _state.value.copy(lastName = newValue)
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    fun onActivityResult(result: String) {
        _state.value = _state.value.copy(resultText = result)
    }

    fun onChangeColor(color: Int) {
        _state.value = _state.value.copy(color = color)
    }

    fun onChangeArrangement(arrangement: Int) {
        _state.value = _state.value.copy(arrangement = arrangement)
    }

    data class State(
        val firstName: String,
        val lastName: String,
        val resultText: String,
        val color: Int,
        val arrangement: Int,
    ) {
        companion object {
            val INITIAL_STATE = State(firstName = "", lastName = "", resultText = "", color = 1, arrangement = 2)
        }
    }
}