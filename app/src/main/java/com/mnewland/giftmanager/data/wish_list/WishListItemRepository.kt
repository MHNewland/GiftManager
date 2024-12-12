package com.mnewland.giftmanager.data.wish_list
import kotlinx.coroutines.flow.Flow

interface WishListItemRepository {
    suspend fun insertWishListItem(wishListItem: WishListItem)
    suspend fun insertWishListItems(wishListItems: List<WishListItem>)
    suspend fun updateWishListItem(wishListItem: WishListItem)
    suspend fun deleteWishListItem(wishListItem: WishListItem)
    fun getWishListItemStream(id: Int): Flow<WishListItem>
    fun getAmazonSyncedItems(personId: Int): Flow<List<WishListItem>>
    fun getAllWishListItemsStream(personId: Int): Flow<List<WishListItem>>
}