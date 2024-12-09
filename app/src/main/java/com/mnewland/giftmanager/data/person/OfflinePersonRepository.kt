package com.mnewland.giftmanager.data.person
import kotlinx.coroutines.flow.Flow

class OfflinePersonRepository(private val personDao: PersonDao) : PersonRepository{
    override fun getAllPeopleStream(): Flow<List<Person>> = personDao.getAllPeople()

    override fun getPersonStream(id: Int): Flow<Person> = personDao.getPerson(id)

    override suspend fun insertPerson(person: Person) = personDao.insert(person)

    override suspend fun deletePerson(person: Person) = personDao.delete(person)

    override suspend fun updatePerson(person: Person) = personDao.update(person)
}