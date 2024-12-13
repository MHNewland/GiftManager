package com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_new_person


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mnewland.giftmanager.AppViewModelProvider
import com.mnewland.giftmanager.R
import com.mnewland.giftmanager.com.mnewland.giftmanager.GiftManagerAppBar
import com.mnewland.giftmanager.com.mnewland.giftmanager.navigation.NavigationDestination
import com.mnewland.giftmanager.data.person.PersonData
import com.mnewland.giftmanager.data.wish_list.WishListItem
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object ProfileDestination : NavigationDestination {
    override val route = "profile"
    override val titleRes = R.string.profile
    const val personIdArg = "personId"
    val routeWithArgs = "$route/{$personIdArg}"
}

@Composable
fun ProfilePage(
    onBackButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
    viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val personData = viewModel.profileUiState.personData
    var showSyncErrorDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            GiftManagerAppBar(
                onBackButtonPressed = onBackButtonClick,
                canNavigateUp = true,
                onSettingsButtonClick = onSettingsButtonClick,
                context = LocalContext.current,
                currentScreen = "${personData.name}'s ${
                    stringResource(ProfileDestination.titleRes)}",
                helpMessage = "You can set the person's information here.\n\n If anything is placed in the" +
                        "\"Purchased Item\" field, it will show a check mark and display " +
                        "what's entered on the main screen.\n\n" +
                        "Currently, the app only syncs with Amazon wish lists that are public. " +
                        "To sync to an amazon list, copy and paste the URL or the ID for the list and click " +
                        "the sync button.\n\n" +
                        "To find the wishlist ID, look for the string of characters after \"www.amazon.com/hz/wishlist/ls/\"."
            )
        }
    ) { innerPadding ->
        ProfileBody(
            updateUiState = {viewModel.updateUiState(it)},
            personData = personData,
            onSyncClick = {

                Log.d("sync", "checking ${personData.wishListItems.isEmpty()}")
                if(
                    //if returns true if successful, false if not
                    !runBlocking {
                        return@runBlocking viewModel.syncAmazonItems(it.listLink)
                    }
                ){
                    showSyncErrorDialog = true
                }
            },
            onUpdatePersonClick = {
                coroutineScope.launch{
                    viewModel.updatePersonData()
                }
            },
            onDeletePersonClick = {
                coroutineScope.launch {
                    viewModel.deletePerson()
                }
                onBackButtonClick()
            },
            modifier = modifier.padding(innerPadding)
        )
        ShowSyncErrorDialog(
            showDialog = showSyncErrorDialog,
            onDismiss = { showSyncErrorDialog = false },
        )
    }
}

@Composable
fun ProfileBody(
    updateUiState: (PersonData) -> Unit,
    personData: PersonData,
    onSyncClick: (PersonData) -> Unit,
    onUpdatePersonClick: () -> Unit,
    onDeletePersonClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
        //.border(width = 2.dp, Color.Red)
    ) {
        Row(
            modifier = Modifier
            //.border(2.dp, Color.Cyan)
        ) {
            TextField(
                value = (personData.name
                        ),
                label = {
                    Text(
                        text = stringResource(R.string.name)
                    )
                },
                onValueChange = {
                    updateUiState(personData.copy(name = it))
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
                value = personData.purchasedItem,
                label = {
                    Text(
                        text = stringResource(R.string.purchased_item)
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onValueChange = {
                    updateUiState(personData.copy(purchasedItem = it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
            //.border(2.dp, Color.LightGray)
        ) {
            TextField(
                value = personData.listLink,
                label = {
                    Text(
                        text = stringResource(R.string.list_link)
                    )
                },
                onValueChange = {
                    updateUiState(personData.copy(listLink = it))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        end = dimensionResource(R.dimen.padding_small)
                    )
            )
            Box(
                modifier = Modifier
                    .width(dimensionResource(R.dimen.button_small_width))
                    .height(dimensionResource(R.dimen.button_small_height))
            ) {
                Button(
                    onClick = {
                        onSyncClick(personData)
                        onUpdatePersonClick()
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .padding(0.dp)
                    //.border(2.dp, Color.Yellow)
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_sync_24),
                        contentDescription = stringResource(R.string.back_button),
                        modifier = Modifier
                    )

                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = dimensionResource(R.dimen.padding_small),
                    end = dimensionResource(R.dimen.padding_small)
                )
                .weight(1f)
                .border(
                    BorderStroke(2.dp,
                        brush = Brush.sweepGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.primary
                            )
                        )
                    )
                )
        ) {
            if (personData.wishListItems.isNotEmpty())
                ItemList(personData.wishListItems)
            else
                Text(
                    text = "No items found",
                    modifier = modifier
                        .align(Alignment.CenterVertically)
                )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_medium))
                //.weight(.2f)
                //.border(2.dp, Color.Magenta)
        ) {
            Button(
                onClick = onUpdatePersonClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(
                    text = stringResource(R.string.update_person)
                )
            }
            Button(
                onClick = onDeletePersonClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(
                    text = stringResource(R.string.delete_person)
                )
            }
        }
    }
}

@Composable
fun ShowSyncErrorDialog(showDialog: Boolean, onDismiss: () -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Sync Error") },
            text = { Text("No items found. Please check the address or wishlist ID and try again.") },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Dismiss")
                }
            }
        )
    }
}

@Composable
fun ItemList(
    itemList: List<WishListItem>,
    modifier: Modifier = Modifier
){
    LazyColumn (
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
        //.border(2.dp, Color.Green)
    ){
        items(itemList){ item ->
            WishlistItemCard(item, modifier)
        }
    }
}

@Composable
fun WishlistItemCard(
    item: WishListItem,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_small))
    ){
        Button(
            onClick = {},
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = MaterialTheme.colorScheme.errorContainer,
                disabledContainerColor = MaterialTheme.colorScheme.error),
            modifier = modifier
                .fillMaxWidth()
        ) {
            Column(modifier = modifier.fillMaxHeight()){
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(item.imageUrl)
                        .crossfade(true)
                        .listener(
                            onStart = { request ->
                                Log.d(
                                    "Coil",
                                    "Image request started: $request"
                                )
                            },
                            onSuccess = { request, metadata ->
                                Log.d(
                                    "Coil",
                                    "Image request succeeded: $request"
                                )
                            },
                            onError = { request, throwable ->
                                Log.e(
                                    "Coil",
                                    "Image request failed: $request"
                                )
                            }
                        )
                        .build(),
                    contentDescription = item.imageUrl,
                    contentScale = ContentScale.FillWidth,
                    modifier = modifier
                        .fillMaxWidth(.25f)
                        .clip(RoundedCornerShape(dimensionResource(R.dimen.image_corner_radius)))
                        .background(Color.Black)
                    //.border(2.dp, Color.Blue)
                )
            }
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(
                        start = dimensionResource(R.dimen.padding_small)
                    )
            ) {
                Text(
                    text = item.title,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
                Text(
                    text = item.price.toString(),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode",
    uiMode = 33)
@Composable
fun WishlistItemPreview() {
    WishlistItemCard(
        WishListItem(
            id = 0,
            title = "test",
            price = "20.99".toDouble(),
            imageUrl = "https://m.media-amazon.com/images/I/51UnZ6EohmL._SS135_.jpg",
            itemUrl = "testURL",
            personId = 0
        )
    )

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode",
    uiMode = 33)
@Composable
fun ProfilePreview() {
        ProfileBody({},PersonData(),{},{},{})
}
