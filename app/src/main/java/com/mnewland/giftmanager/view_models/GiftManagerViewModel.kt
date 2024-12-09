package com.mnewland.giftmanager.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mnewland.giftmanager.data.person.Person
import com.mnewland.giftmanager.data.person.PersonRepository
import com.mnewland.giftmanager.data.wish_list.WishList
import com.mnewland.giftmanager.data.wish_list.WishListItem
import com.mnewland.giftmanager.data.wish_list.WishListItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class GiftManagerViewModel (
    savedStateHandle: SavedStateHandle,
    private val personRepository: PersonRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(
        GiftManagerUIState(
            personList = personRepository.getAllPeopleStream(),
            currentPersonId = 0
        )
    )

    val uiState: StateFlow<GiftManagerUIState> = _uiState

    fun updateCurrentPerson(selectedPerson: Person) {
        _uiState.value.currentPersonId = selectedPerson.id
    }

    suspend fun updatePersonData(selectedPerson: Person) {
        personRepository.updatePerson(selectedPerson)
    }

    suspend fun addPerson(person: Person) {
        personRepository.insertPerson(person)
    }

    fun getFullPersonList(): Flow<List<Person>>{
        return personRepository.getAllPeopleStream()
    }

    fun getPerson(id: Int): Flow<Person>{
        return personRepository.getPersonStream(id)
    }

    fun getCurrentPerson(): Flow<Person>{
        return personRepository.getPersonStream(uiState.value.currentPersonId)
    }

}

data class GiftManagerUIState(
    val personList: Flow<List<Person>>,
    var currentPersonId: Int
)

data class PersonDetails(
    val id: Int = 0,
    val name: String = "",
    val listLink: String = "",
    val purchasedItem: String = "",
    val wishListId: Int = 0
)