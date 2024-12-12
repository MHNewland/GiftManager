package com.mnewland.giftmanager.com.mnewland.giftmanager.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mnewland.giftmanager.data.person.Person
import com.mnewland.giftmanager.data.person.PersonDao
import com.mnewland.giftmanager.data.wish_list.WishListItem
import com.mnewland.giftmanager.data.wish_list.WishListItemDao

@Database(entities = [Person::class, WishListItem::class], version = 5, exportSchema = false)
abstract class GiftManagerDatabase : RoomDatabase(){
    abstract fun personDao(): PersonDao
    abstract fun wishListItemDao(): WishListItemDao
    companion object{
        @Volatile
        private var Instance: GiftManagerDatabase? = null
        fun getDatabase(context: Context): GiftManagerDatabase {
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, GiftManagerDatabase::class.java, "GiftManager")
                    .fallbackToDestructiveMigration()
                    //.createFromAsset("database/gift_manager.sql")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}