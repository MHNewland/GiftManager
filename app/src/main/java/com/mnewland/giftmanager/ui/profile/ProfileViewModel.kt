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
import com.mnewland.giftmanager.data.wish_list.WishListItem
import com.mnewland.giftmanager.data.wish_list.WishListItemRepository
import com.mnewland.giftmanager.network.amazonParser
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProfileViewModel (
    savedStateHandle: SavedStateHandle,
    private val personRepository: PersonRepository,
    private val wishListItemRepository: WishListItemRepository
): ViewModel() {

    private val personId: Int = checkNotNull(savedStateHandle[ProfileDestination.personIdArg])

    var profileUiState by mutableStateOf(ProfileUiState())
        private set

    init {
        Log.d("ProfileViewModel", "init run")
        viewModelScope.launch {
            val wishListItems: List<WishListItem> =
                wishListItemRepository
                    .getAllWishListItemsStream(personId)
                    .first()

            profileUiState = personRepository.getPersonStream(personId)
                .filterNotNull()
                .first()
                .toProfileUiState()
            //I know there's probably a better way of doing this
            //but I don't have the time to figure it out at the moment.
            profileUiState = profileUiState
                .copy(
                    personData = profileUiState.personData
                        .copy(
                            wishListItems = wishListItems
                        )
                )
        }

    }

    fun updateUiState(personData: PersonData){
        profileUiState = ProfileUiState(personData = personData)
    }

    suspend fun updatePersonData() {
        val rowsChanged = personRepository.updatePerson(profileUiState.personData.toPerson())
        Log.d("update Person", "rows changed: $rowsChanged")
    }

    suspend fun deletePerson(){
        personRepository.deletePerson(personRepository.getPersonStream(personId).first())
    }

    fun syncAmazonItems(listLink: String):Boolean{
        return runBlocking {
            Log.d("syncAmazonItems", "start")
            val items = amazonParser(listLink, personId)
            if(items.isEmpty()) return@runBlocking false
            val amazonSyncedItems =
                wishListItemRepository
                    .getAmazonSyncedItems(personId)
                    .map {
                        WishList(
                            wishListItems = it
                        )
                    }
                    .first()
            amazonSyncedItems.wishListItems.forEach {
                wishListItemRepository.deleteWishListItem(it)
            }
            Log.d("syncAmazonItems", items.count().toString())
            wishListItemRepository.insertWishListItems(items)
            profileUiState = wishListItemRepository
                .getAllWishListItemsStream(personId)
                .map {
                    ProfileUiState(
                        personData = profileUiState
                            .personData
                            .copy(wishListItems = items)
                    )
                }
                .first()
            profileUiState.personData.wishListItems.forEach {
                Log.d("WishList after sync: ", it.toWishListItemData().toString())

            }
            return@runBlocking true
        }
    }
}

data class ProfileUiState(
    val personData: PersonData = PersonData(),
)

fun Person.toProfileUiState() =
    ProfileUiState(
        personData = this.toPersonData()
    )

data class WishList(
    val wishListItems: List<WishListItem> = emptyList()
)