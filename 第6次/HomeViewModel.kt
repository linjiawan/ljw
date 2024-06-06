package com.example.zxy.ui.home

import androidx.lifecycle.ViewModel
import com.example.zxy.data.User

class HomeViewModel() : ViewModel() {
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class HomeUiStata(val itemlist:List<User> = listOf())