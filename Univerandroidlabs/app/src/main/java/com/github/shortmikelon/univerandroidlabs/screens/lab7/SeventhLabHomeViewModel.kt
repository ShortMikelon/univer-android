package com.github.shortmikelon.univerandroidlabs.screens.lab7

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SeventhLabHomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    fun onImageButtonStateChanged(newButtonState: String) {
        _state.value = _state.value.copy(imageButtonState = newButtonState)
    }

    fun onChooseMeChanged(newValue: Boolean) {
        _state.value = _state.value.copy(chooseMe = newValue, imageButtonState = if (newValue) FOCUSED else NORMAL)
    }

    fun onMuteClicked(newValue: Boolean) {
        _state.value = _state.value.copy(isMuted = newValue)
    }

    fun onChangeOption(option: String) {
        if (!OPTIONS.contains(option)) return
        _state.value = _state.value.copy(selected = option)
    }

    fun onFieldChanged(newValue: String) {
        _state.value = _state.value.copy(field = newValue)
    }

    fun onClearClicked() {
        _state.value = SeventhLabHomeViewModel.State()
    }

    data class State(
        val imageButtonState: String = NORMAL,
        val chooseMe: Boolean = false,
        val isMuted: Boolean = false,
        val selected: String = OPTIONS.first(),
        val field: String = "",
    )

    companion object {
         val OPTIONS = listOf(
            "Собачка",
            "Кошечка",
            "Кролик"
        )

        const val NORMAL = "normal"
        const val FOCUSED = "focused"
        const val CLICKED = "clicked"
    }
}