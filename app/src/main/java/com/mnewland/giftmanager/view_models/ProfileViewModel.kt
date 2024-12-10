package com.mnewland.giftmanager.com.mnewland.giftmanager.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnewland.giftmanager.data.person.Person
import com.mnewland.giftmanager.data.person.PersonData
import com.mnewland.giftmanager.data.person.PersonRepository
import com.mnewland.giftmanager.ui.ProfileDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel (
    savedStateHandle: SavedStateHandle,
    private val personRepository: PersonRepository
): ViewModel() {

    private val personId: Int = -1//checkNotNull(savedStateHandle[ProfileDestination.personIdArg])

    val uiState: StateFlow<ProfileUiState> =
        personRepository.getPersonStream(id = personId )
            .filterNotNull()
            .map {
                ProfileUiState(personData = it.toPersonData() )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ProfileUiState()
            )

    var profileUiState by mutableStateOf(ProfileUiState())
        private set

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun updateUiState(personData: PersonData){
        profileUiState = ProfileUiState(personData = personData)
    }

    suspend fun updatePersonData(selectedPerson: Person) {
        personRepository.updatePerson(selectedPerson)
    }

    suspend fun addPerson(person: Person) {
        personRepository.insertPerson(person)
    }
}

data class ProfileUiState(
    val personData: PersonData = PersonData(),
)

fun Person.toPersonData(): PersonData =
    PersonData(
        name = name,
        listLink = listLink,
        purchasedItem = purchasedItem
    )


fun Person.toProfileUiState() =
    ProfileUiState(
        personData = this.toPersonData()
    )

fun PersonData.toPerson() =
    Person(
        id = id,
        name = name,
        listLink = listLink,
        purchasedItem = purchasedItem,
        wishListId = wishListId
    )