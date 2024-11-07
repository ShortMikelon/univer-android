package com.github.shortmikelon.univerandroidlabs.screens.sixthlab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shortmikelon.univerandroidlabs.Dependencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SixthLabDetailsViewModel : ViewModel() {
    private val dao: SixthLabDao = Dependencies.getDao()

    private val _state = MutableStateFlow(SixthLabEntity())
    val state get() = _state.asStateFlow()

    fun onChangeField(fieldIndex: Int, newValue: String) {
        if (fieldIndex == 1) {
            _state.value = _state.value.copy(name = newValue)
        } else {
            _state.value = _state.value.copy(email = newValue)
        }
    }

    fun save() {
        viewModelScope.launch {
            val entity = SixthLabEntity(name = _state.value.name, email = _state.value.email)
            val id = dao.insert(entity)
            fetchRecord(id)
        }
    }

    fun fetchRecord(id: Long) {
        viewModelScope.launch {
            _state.value = if (id == 0L) SixthLabEntity() else dao.findById(id)
        }
    }

    fun update() {
        viewModelScope.launch {
            dao.update(_state.value)
            fetchRecord(_state.value.id)
        }
    }

    fun delete() {
        viewModelScope.launch {
            dao.delete(_state.value)
            fetchRecord(0L)
        }
    }
}