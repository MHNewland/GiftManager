package com.mnewland.giftmanager.data

import com.mnewland.giftmanager.model.Person
import com.mnewland.giftmanager.model.WishlistItem

object Wishlist {
    private val wishist: MutableList<WishlistItem> = emptyList<WishlistItem>().toMutableList()
    fun getWishlist(): List<WishlistItem> {
        return Wishlist.wishist
    }
}