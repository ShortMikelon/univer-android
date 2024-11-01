package com.github.shortmikelon.univerandroidlabs.screens.animations

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.abs

class AnimationsViewModel : ViewModel() {

    private val _state = MutableStateFlow(AnimationsState())
    val state = _state.asStateFlow()

    fun onClickLeftArrow() {
        if (_state.value.boxLocation == AnimationsState.LOCATION_LEFT) return
        _state.update { copy(boxLocation = this.boxLocation - 1) }
    }

    fun onClickRightArrow() {
        if (_state.value.boxLocation == AnimationsState.LOCATION_RIGHT) return
        _state.update { copy(boxLocation = this.boxLocation + 1) }
    }

    fun onChangeColor() {
//            _state.tryEmit(_state.value.copy(boxColor = abs(_state.value.boxColor - 1)))
        viewModelScope.launch {
            Log.d("TAGGG", "MutableStateFlow old value: ${state.value}")
            _state.value = AnimationsState(boxColor = AnimationsState.BLUE)
            Log.d("TAGGG", "MutableStateFlow new value: ${state.value}")
        }

    }

    fun onChangeRotation() {
        _state.update {
            copy(
                rotation = if (this.rotation == AnimationsState.ANGLE_0) AnimationsState.ANGLE_45 else AnimationsState.ANGLE_0
            )
        }
    }

    fun onChangeTransparent() {
        _state.update {
            copy(
                transparent = if (this.transparent == AnimationsState.TRANSPARENT) AnimationsState.VISIBLE else AnimationsState.TRANSPARENT
            )
        }
    }

    private fun MutableStateFlow<AnimationsState>.update(block: AnimationsState.() -> AnimationsState) {
        viewModelScope.launch { this@update.emit(block(this@update.value)) }
    }

}