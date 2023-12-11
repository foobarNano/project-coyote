package com.coyote.android.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.coyote.android.models.AppData

class MainViewModel : ViewModel() {

    var isActive by mutableStateOf(false)
        private set

    var appShown: AppData? = null
        private set

    fun showInfo(app: AppData) {
        appShown = app
        isActive = true
    }

    fun hideInfo() {
        isActive = false
    }
}