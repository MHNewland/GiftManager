package com.mnewland.giftmanager.data.person

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mnewland.giftmanager.data.wish_list.WishListItem

@Database(entities = [Person::class, WishListItem::class], version = 1, exportSchema = false)
abstract class PersonDatabase : RoomDatabase(){
    abstract fun personDao(): PersonDao
    companion object{
        @Volatile
        private var Instance: PersonDatabase? = null
        fun getDatabase(context: Context): PersonDatabase{
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, PersonDatabase::class.java, "PersonList")
                    .fallbackToDestructiveMigration()
                    //.createFromAsset("database/person_list.sql")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}