package com.mnewland.giftmanager

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mnewland.giftmanager.view_models.GiftManagerViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            GiftManagerViewModel(
                this.createSavedStateHandle(),
                giftManagerApplication().container.personRepository
            )
        }
    }
}


fun CreationExtras.giftManagerApplication(): GiftManagerApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as GiftManagerApplication)