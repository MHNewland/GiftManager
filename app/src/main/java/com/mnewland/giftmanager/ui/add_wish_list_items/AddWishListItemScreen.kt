package com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_wish_list_items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mnewland.giftmanager.AppViewModelProvider
import com.mnewland.giftmanager.R
import com.mnewland.giftmanager.com.mnewland.giftmanager.GiftManagerAppBar
import com.mnewland.giftmanager.com.mnewland.giftmanager.navigation.NavigationDestination
import com.mnewland.giftmanager.data.wish_list.WishListItemData
import kotlinx.coroutines.launch


object AddWishListItemsDestination : NavigationDestination {
    override val route = "add_wish_list_items"
    override val titleRes = R.string.wish_list
}
@Composable
fun AddWishListItemPage(
    onBackButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
    viewModel: AddWishListItemViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
){
    val uiState = viewModel.addNewWishListItemUiState
    val wishListItemData = uiState.wishListItemData
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        topBar = {
            GiftManagerAppBar(
                onBackButtonPressed = onBackButtonClick,
                canNavigateUp = true,
                onSettingsButtonClick = onSettingsButtonClick,
                context = LocalContext.current,
                currentScreen = stringResource(AddWishListItemsDestination.titleRes),
                helpMessage = "Enter the wishListItem's name and click \"Add WishListItem\" to add them to the list.\n" +
                        "It will then take you to their profile where you can fill out the rest of their information."
            )
        }
    ) { innerPadding ->
        WishListItemBody(
            updateUiState = {viewModel.updateUiState(it)},
            addWishListItem = {
                coroutineScope.launch {
                    viewModel.addWishListItem()
                }
            },
            wishListItemData = wishListItemData,
            modifier = modifier.padding(innerPadding)
        )
    }
}


@Composable
fun WishListItemBody(
    updateUiState: (WishListItemData) -> Unit,
    addWishListItem: () -> Unit,
    wishListItemData: WishListItemData,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
        ) {
            TextField(
                value = (wishListItemData.title
                        ),
                label = {
                    Text(
                        text = stringResource(R.string.name)
                    )
                },
                onValueChange = {
                    updateUiState(wishListItemData.copy(title = it))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
            )
        }
        Row(
            modifier = Modifier
        ) {
            TextField(
                value = (wishListItemData.price
                        ),
                label = {
                    Text(
                        text = stringResource(R.string.price)
                    )
                },
                leadingIcon = {
                    Text(
                        text = "$"
                    )
                },
                onValueChange = {
                    updateUiState(wishListItemData.copy(price = it))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
            )
        }
        Row(
            modifier = Modifier
        ) {
            TextField(
                value = (wishListItemData.itemUrl
                        ),
                label = {
                    Text(
                        text = stringResource(R.string.item_url)
                    )
                },
                onValueChange = {
                    updateUiState(wishListItemData.copy(title = it))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
            )
        }
        Row(
            modifier = Modifier
        ) {
            TextField(
                value = (wishListItemData.imageUrl
                        ),
                label = {
                    Text(
                        text = stringResource(R.string.image_url)
                    )
                },
                onValueChange = {
                    updateUiState(wishListItemData.copy(title = it))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
            )
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
            //.border(2.dp, Color.Magenta)
        ) {
            Button(
                onClick = addWishListItem,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius))
            ) {
                Text(
                    text = stringResource(R.string.add_item)
                )
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode",
    uiMode = 33)
@Composable
fun AddWishListItemsPreview() {
    WishListItemBody({},{},WishListItemData())

}
