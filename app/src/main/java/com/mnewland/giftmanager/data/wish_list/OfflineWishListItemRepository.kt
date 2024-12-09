package com.mnewland.giftmanager.data.wish_list

import kotlinx.coroutines.flow.Flow

class OfflineWishListItemRepository(private val wishListItemDao: WishListItemDao): WishListItemRepository {
    override fun getAllWishListItemsStream(): Flow<List<WishListItem>> = wishListItemDao.getAllWishListItems()

    override fun getWishListItemStream(id: Int): Flow<WishListItem> = wishListItemDao.getWishListItem(id)

    override suspend fun insertWishListItem(wishListItem: WishListItem) = wishListItemDao.insert(wishListItem)

    override suspend fun deleteWishListItem(wishListItem: WishListItem) = wishListItemDao.delete(wishListItem)

    override suspend fun updateWishListItem(wishListItem: WishListItem) = wishListItemDao.update(wishListItem)

}