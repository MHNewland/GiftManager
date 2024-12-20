package com.mnewland.giftmanager.data.wish_list

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mnewland.giftmanager.data.wish_list.WishListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface WishListItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wishListItem: WishListItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wishListItems: List<WishListItem>)

    @Update
    suspend fun update(wishListItem: WishListItem)

    @Delete
    suspend fun delete(wishListItem: WishListItem)

    @Query("SELECT * from WishListItems WHERE id = :id")
    fun getWishListItem(id: Int): Flow<WishListItem>

    @Query("SELECT * FROM WishListItems WHERE amazonSynced = 1 AND personId = :personId")
    fun getAmazonSyncedItems(personId: Int): Flow<List<WishListItem>>

    @Query("SELECT * from WishListItems WHERE personId = :personId ORDER BY title")
    fun getAllWishListItems(personId: Int): Flow<List<WishListItem>>

}