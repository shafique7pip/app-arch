package com.example.apparch.store.pres.util.components


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apparch.util.EventBus
import kotlinx.coroutines.launch

fun ViewModel.sendEvent(event: Any) {
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}