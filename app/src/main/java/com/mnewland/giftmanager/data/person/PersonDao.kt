package com.mnewland.giftmanager.data.person

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: Person): Long

    @Update
    suspend fun update(person: Person): Int

    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * from PersonList WHERE id = :id")
    fun getPerson(id: Int): Flow<Person>

    @Query("SELECT * from PersonList ORDER BY name")
    fun getAllPeople(): Flow<List<Person>>

}