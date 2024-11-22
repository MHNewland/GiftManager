package com.mnewland.giftmanager.model

data class Person(
    val id: Int = 0,
    val name: String = "",
    val listLink: String = "",
    val purchasedItem: String = "",
    val wishList: List<WishlistItem> = emptyList()
)
