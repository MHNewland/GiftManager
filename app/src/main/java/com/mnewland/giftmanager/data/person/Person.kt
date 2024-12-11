package com.mnewland.giftmanager.data.person

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_new_person.ProfileUiState
import com.mnewland.giftmanager.data.wish_list.WishListItem

@Entity(
    tableName = "PersonList",
    foreignKeys = [ForeignKey(
        entity = WishListItem::class,
        parentColumns = ["id"],
        childColumns = ["wishListId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val listLink: String = "",
    val purchasedItem: String = "",
    val wishListId: Int? = null
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
    val wishListId: Int? = null
){
    fun toPerson() =
        Person(
            id = id,
            name = name,
            listLink = listLink,
            purchasedItem = purchasedItem,
            wishListId = wishListId
        )

    override fun toString(): String{
        return "$id, ${name}, ${purchasedItem}, ${listLink}"
    }
}