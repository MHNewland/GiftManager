package com.mnewland.giftmanager

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_new_person.AddNewPersonViewModel
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_new_person.ProfileViewModel
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_wish_list_items.AddNewWishListItemUiState
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_wish_list_items.AddWishListItemViewModel
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.home.GiftManagerViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            GiftManagerViewModel(
                this.createSavedStateHandle(),
                giftManagerApplication().container.personRepository
            )
        }
        initializer{
            AddNewPersonViewModel(
                giftManagerApplication().container.personRepository
            )
        }
        initializer {
            ProfileViewModel(
                this.createSavedStateHandle(),
                giftManagerApplication().container.personRepository,
                giftManagerApplication().container.wishListItemRepository
            )
        }
        initializer{
            AddWishListItemViewModel(
                giftManagerApplication().container.wishListItemRepository
            )
        }
    }
}


fun CreationExtras.giftManagerApplication(): GiftManagerApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as GiftManagerApplication)