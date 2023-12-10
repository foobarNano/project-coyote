package com.coyote.android.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var isShowingMore by mutableStateOf(false)
        private set

    fun showInfo() {
        isShowingMore = true
    }

    fun hideInfo() {
        isShowingMore = false
    }
}