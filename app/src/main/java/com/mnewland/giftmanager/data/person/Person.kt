package com.mnewland.giftmanager.data.person

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_new_person.ProfileUiState
import com.mnewland.giftmanager.data.wish_list.WishListItem

@Entity(tableName = "PersonList")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val listLink: String = "",
    val purchasedItem: String = "",
){
    fun toPersonData(): PersonData =
        PersonData(
            id = id,
            name = name,
            listLink = listLink,
            purchasedItem = purchasedItem
        )
}

data class PersonData(
    val id: Int = 0,
    val name: String = "",
    val listLink: String = "",
    val purchasedItem: String = "",
    val wishListItems: List<WishListItem> = emptyList(),
){
    fun toPerson() =
        Person(
            id = id,
            name = name,
            listLink = listLink,
            purchasedItem = purchasedItem,
        )

    override fun toString(): String{
        return "$id, ${name}, ${purchasedItem}, ${listLink}"
    }
}