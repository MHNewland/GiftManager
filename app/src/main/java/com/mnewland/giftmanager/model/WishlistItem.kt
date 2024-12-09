package com.mnewland.giftmanager.model;

data class WishlistItem(
    val title: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val itemUrl: String = "",
    val amazonSynced: Boolean = false
)