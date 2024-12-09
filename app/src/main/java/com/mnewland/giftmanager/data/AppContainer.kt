package com.mnewland.giftmanager.data

import android.content.Context
import com.mnewland.giftmanager.data.person.OfflinePersonRepository
import com.mnewland.giftmanager.data.person.PersonDatabase
import com.mnewland.giftmanager.data.person.PersonRepository
import com.mnewland.giftmanager.data.wish_list.OfflineWishListItemRepository
import com.mnewland.giftmanager.data.wish_list.WishListDatabase
import com.mnewland.giftmanager.data.wish_list.WishListItemRepository

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val personRepository: PersonRepository
    val wishListItemRepository: WishListItemRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val personRepository: PersonRepository by lazy {
        OfflinePersonRepository(PersonDatabase.getDatabase(context).personDao())
    }

    override val wishListItemRepository: WishListItemRepository by lazy {
        OfflineWishListItemRepository(WishListDatabase.getDatabase(context).wishListDao())
    }
}