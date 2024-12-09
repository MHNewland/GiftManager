package com.mnewland.giftmanager.data.wish_list

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mnewland.giftmanager.data.person.Person

@Database(entities = [WishListItem::class, Person::class], version = 1, exportSchema = false)
abstract class WishListDatabase : RoomDatabase(){
    abstract fun wishListDao(): WishListItemDao
    companion object{
        @Volatile
        private var Instance: WishListDatabase? = null
        fun getDatabase(context: Context): WishListDatabase {
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, WishListDatabase::class.java, "WishListItems")
                    .fallbackToDestructiveMigration()
                    .createFromAsset("database/wish_list_items.sql")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}