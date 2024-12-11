package com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_new_person

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnewland.giftmanager.data.person.Person
import com.mnewland.giftmanager.data.person.PersonData
import com.mnewland.giftmanager.data.person.PersonRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel (
    savedStateHandle: SavedStateHandle,
    private val personRepository: PersonRepository
): ViewModel() {

    private val personId: Int = checkNotNull(savedStateHandle[ProfileDestination.personIdArg])

    var profileUiState by mutableStateOf(ProfileUiState())
        private set

    init {
        viewModelScope.launch {
            profileUiState = personRepository.getPersonStream(personId)
                .filterNotNull()
                .first()
                .toProfileUiState()
        }

    }


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

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun updateUiState(personData: PersonData){
        profileUiState = ProfileUiState(personData = personData)
    }

    suspend fun updatePersonData() {
        Log.d("update Person", profileUiState.personData.toString())
        val rowsChanged = personRepository.updatePerson(profileUiState.personData.toPerson())
        Log.d("update Person", "rows changed: $rowsChanged")
    }
}

data class ProfileUiState(
    val personData: PersonData = PersonData(),
)

fun Person.toProfileUiState() =
    ProfileUiState(
        personData = this.toPersonData()
    )