package com.mnewland.giftmanager.data.person

import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun insertPerson(person: Person): Long
    suspend fun updatePerson(person: Person): Int
    suspend fun deletePerson(person: Person)
    fun getPersonStream(id: Int): Flow<Person>
    fun getAllPeopleStream(): Flow<List<Person>>
}