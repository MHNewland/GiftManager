package com.mnewland.giftmanager.data.wish_list;

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mnewland.giftmanager.data.person.Person

@Entity(
    tableName = "WishListItems",
    foreignKeys = [ForeignKey(
        entity = Person::class,
        parentColumns = ["id"],
        childColumns = ["personId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class WishListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val itemUrl: String = "",
    val amazonSynced: Boolean = false,
    val personId: Int = 0
){
    fun toWishListItemData() =
        WishListItemData(
            id = id,
            title = title,
            price = price.toString(),
            imageUrl = imageUrl,
            itemUrl = itemUrl,
            amazonSynced = amazonSynced,
            personId = personId
        )

}

data class WishListItemData(
    val id: Int = 0,
    val title: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val itemUrl: String = "",
    val amazonSynced: Boolean = false,
    val personId: Int = 0
){
    fun toWishListItem() =
        WishListItem(
            id = id,
            title = title,
            price = price.toDoubleOrNull() ?: 0.0,
            imageUrl = imageUrl,
            itemUrl = itemUrl,
            amazonSynced = amazonSynced,
            personId = personId
        )
}
