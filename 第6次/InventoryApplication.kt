package com.example.zxy

import android.app.Application
import com.example.zxy.data.AppContainer
import com.example.zxy.data.AppDataContainer

class InventoryApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}