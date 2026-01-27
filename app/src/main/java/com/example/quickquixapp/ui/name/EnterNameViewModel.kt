package com.example.quickquixapp.ui.name

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class EnterNameViewModel : ViewModel() {

    var userName by mutableStateOf("")
        private set

    val isNameValid: Boolean
        get() = userName.trim().isNotEmpty()

    fun updateName(name: String) {
        userName = name
    }
}