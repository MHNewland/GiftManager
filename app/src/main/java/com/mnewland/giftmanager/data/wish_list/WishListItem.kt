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
        onDelete = ForeignKey.NO_ACTION
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
)