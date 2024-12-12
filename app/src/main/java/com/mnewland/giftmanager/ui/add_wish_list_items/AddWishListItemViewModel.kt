package com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_wish_list_items

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mnewland.giftmanager.data.wish_list.WishListItemData
import com.mnewland.giftmanager.data.wish_list.WishListItemRepository

class AddWishListItemViewModel (
    private val wishListItemRepository: WishListItemRepository
): ViewModel() {

    var addNewWishListItemUiState by mutableStateOf(AddNewWishListItemUiState())
        private set

    fun updateUiState(wishListItemData: WishListItemData) {
        addNewWishListItemUiState = AddNewWishListItemUiState(wishListItemData = wishListItemData)
    }

    suspend fun addWishListItem() {
        wishListItemRepository.insertWishListItem(addNewWishListItemUiState.wishListItemData.toWishListItem())
    }
}

data class AddNewWishListItemUiState(
    val wishListItemData: WishListItemData = WishListItemData(),
)
