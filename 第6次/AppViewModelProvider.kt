package com.example.zxy.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.zxy.InventoryApplication
import com.example.zxy.ui.home.HomeViewModel
import com.example.zxy.ui.item.ItemDetailsViewModel
import com.example.zxy.ui.item.ItemEditViewModel
import com.example.zxy.ui.item.ItemEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ItemEditViewModel(
                this.createSavedStateHandle()
            )
        }
        initializer {
            ItemEntryViewModel(inventoryApplication().container.usersRepository)
        }
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle()
            )
        }
        initializer {
            HomeViewModel()
        }
    }
}

fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)