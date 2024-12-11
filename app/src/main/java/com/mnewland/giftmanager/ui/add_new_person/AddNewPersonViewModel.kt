package com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_new_person

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mnewland.giftmanager.data.person.PersonData
import com.mnewland.giftmanager.data.person.PersonRepository

class AddNewPersonViewModel (
    private val personRepository: PersonRepository
): ViewModel() {


    var addNewPersonUiState by mutableStateOf(AddNewPersonUiState())
        private set

    fun updateUiState(personData: PersonData){
        addNewPersonUiState = AddNewPersonUiState(personData = personData)
    }

    suspend fun addPerson(): Int {
        val addedPersonId = personRepository.insertPerson(addNewPersonUiState.personData.toPerson())
        return addedPersonId.toInt()
    }
}

data class AddNewPersonUiState(
    val personData: PersonData = PersonData(),
)

