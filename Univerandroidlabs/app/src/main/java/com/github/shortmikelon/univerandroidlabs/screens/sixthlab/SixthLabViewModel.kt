package com.github.shortmikelon.univerandroidlabs.screens.sixthlab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shortmikelon.univerandroidlabs.Dependencies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SixthLabViewModel : ViewModel() {

    private val dao: SixthLabDao = Dependencies.getDao()

    private val _state = MutableStateFlow(emptyList<SixthLabEntity>())
    val state get() = _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _state.value = dao.findAll()
            }
        }
    }
}