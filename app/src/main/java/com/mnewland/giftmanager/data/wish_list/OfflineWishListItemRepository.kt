package com.mnewland.giftmanager.data.wish_list

import kotlinx.coroutines.flow.Flow

class OfflineWishListItemRepository(private val wishListItemDao: WishListItemDao): WishListItemRepository {
    override fun getAllWishListItemsStream(personId: Int): Flow<List<WishListItem>> = wishListItemDao.getAllWishListItems(personId)

    override fun getWishListItemStream(id: Int): Flow<WishListItem> = wishListItemDao.getWishListItem(id)

    override fun getAmazonSyncedItems(personId: Int): Flow<List<WishListItem>> = wishListItemDao.getAmazonSyncedItems(personId)

    override suspend fun insertWishListItem(wishListItem: WishListItem) = wishListItemDao.insert(wishListItem)
    override suspend fun insertWishListItems(wishListItems: List<WishListItem>) = wishListItemDao.insert(wishListItems)

    override suspend fun deleteWishListItem(wishListItem: WishListItem) = wishListItemDao.delete(wishListItem)

    override suspend fun updateWishListItem(wishListItem: WishListItem) = wishListItemDao.update(wishListItem)

}