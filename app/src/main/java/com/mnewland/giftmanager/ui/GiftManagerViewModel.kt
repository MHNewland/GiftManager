package com.mnewland.giftmanager.ui

import androidx.lifecycle.ViewModel
import com.mnewland.giftmanager.data.PersonList
import com.mnewland.giftmanager.model.Person
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class GiftManagerViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        GiftManagerUIState(
            personList = PersonList.getPersonList(),
            currentPerson = PersonList.getPersonList().getOrElse(0) {
                PersonList.defaultPerson
            }
        )
    )

    val uiState: StateFlow<GiftManagerUIState> = _uiState

    fun updateCurrentPerson(selectedPerson: Person) {
        _uiState.update {
            it.copy(currentPerson = selectedPerson)
        }
    }

    fun updatePersonData(selectedPerson: Person) {
        val updatedList = _uiState.value.personList.map { person ->
            if (person.id == selectedPerson.id) {
                selectedPerson
            } else {
                person
            }
        }
        _uiState.update { it.copy(personList = updatedList) }
    }


    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }


    fun navigateToProfilePage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }

    fun addPerson(person: Person){
        var newID: Int
        do {
            newID = Random.nextInt()
        }while(_uiState.value.personList.any{it.id == newID})

        val newPerson = person.copy(
            id = newID
        )
        _uiState.update {currentState ->
            currentState.copy(personList = currentState.personList+newPerson)

        }
    }
}

data class GiftManagerUIState(
    val personList: List<Person> = emptyList(),
    val currentPerson: Person = PersonList.defaultPerson,
    val isShowingListPage: Boolean = true
)