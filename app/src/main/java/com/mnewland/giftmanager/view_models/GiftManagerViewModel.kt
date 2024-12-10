package com.mnewland.giftmanager.view_models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnewland.giftmanager.data.person.Person
import com.mnewland.giftmanager.data.person.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class GiftManagerViewModel (
    savedStateHandle: SavedStateHandle,
    private val personRepository: PersonRepository
): ViewModel() {

    private val _uiState: StateFlow<GiftManagerUiState> =
        personRepository.getAllPeopleStream()
                .map {
                    GiftManagerUiState(it)
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = GiftManagerUiState()
                )



    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val giftManagerUiState: StateFlow<GiftManagerUiState> = _uiState

    fun updateCurrentPerson(selectedPerson: Person) {
        _uiState.value.currentPersonId = selectedPerson.id
    }

    fun getFullPersonList(): Flow<List<Person>>{
        return personRepository.getAllPeopleStream()
    }

    fun getCurrentPerson(): Flow<Person>{
        if(giftManagerUiState.value.currentPersonId != -1)
            return personRepository.getPersonStream(giftManagerUiState.value.currentPersonId)
        else
            return flowOf(Person())
    }
}

data class GiftManagerUiState(
    val personList: List<Person> = emptyList(),
    var currentPersonId: Int = -1
)

