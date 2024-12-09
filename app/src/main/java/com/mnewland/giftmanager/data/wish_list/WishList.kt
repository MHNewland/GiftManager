package com.mnewland.giftmanager.data.wish_list

object WishList {
    private val wishist: MutableList<WishListItem> = emptyList<WishListItem>().toMutableList()
    fun getWishlist(): List<WishListItem> {
        return wishist
    }
}