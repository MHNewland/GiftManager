package com.mnewland.giftmanager.ui


import android.text.Editable.Factory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.mnewland.giftmanager.data.person.PersonList
import com.mnewland.giftmanager.data.person.Person
import com.mnewland.giftmanager.data.wish_list.WishListItem
import com.mnewland.giftmanager.network.amazonParser
import com.mnewland.giftmanager.ui.theme.GiftManagerAppTheme
import com.mnewland.giftmanager.view_models.GiftManagerViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfilePage(
    viewModel: GiftManagerViewModel,
    onValueChanged: (Person) -> Unit,
    onAddButtonClicked: (Person) -> Unit,
    onEditButtonClicked: (Person) -> Unit,
    onSyncButtonClicked: (Person) -> Unit,
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    var showSyncErrorDialog by remember { mutableStateOf(false) }
    val person = viewModel.getCurrentPerson().collectAsState(Person())

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
                value = (
                    "test"
                ),
                label = {
                    Text(
                        text = stringResource(R.string.name)
                    )
                },
                onValueChange = {
//                    onValueChanged(
//                        person.copy(name = it)
//                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
            )
        }
        Row (
            modifier = Modifier
                //.border(2.dp, Color.Yellow)
        ){
            TextField(
                value = person.value.purchasedItem,
                label = {
                    Text(
                        text = stringResource(R.string.purchaced_item)
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
//                        if (person.id == 0) {
//                            onAddButtonClicked(person)
//                        } else {
//                            onEditButtonClicked(person)
//                        }
                    }
                ),
                onValueChange = {
//                    onValueChanged(
//                        person.copy(purchasedItem = it)
//                    )
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
                value = person.value.wishListId.toString(),
                label = {
                    Text(
                        text = stringResource(R.string.list_link)
                    )
                },
                onValueChange = {
//                    onValueChanged(
//                        person.copy(listLink = it)
//                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        end = dimensionResource(R.dimen.padding_small)
                    )
                //.border(width = 2.dp, Color.LightGray)
            )
            Box(modifier = Modifier
                .width(dimensionResource(R.dimen.button_small_width))
                .height(dimensionResource(R.dimen.button_small_height))
            ){
                Button(
                    onClick = {

                        /*coroutineScope.launch {
                            val wishlist = amazonParser(person.listLink)
                            if(wishlist.isNotEmpty()) {
                                val updatedPerson = person.copy(wishList = wishlist)
                                onSyncButtonClicked(updatedPerson)
                            }else {
                                showSyncErrorDialog = true
                            }
                        }*/
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
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(R.dimen.padding_small),
                    end = dimensionResource(R.dimen.padding_small)
                )
                .weight(1f)
                //.border(2.dp, Color.Cyan)
        ) {
            ShowSyncErrorDialog(
                showDialog = showSyncErrorDialog,
                onDismiss = { showSyncErrorDialog = false },
            )
            if(person.value.wishListId!=0)
                //ItemList()
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
                //.border(2.dp, Color.Magenta)
        ) {
            Button(
                onClick = {
//                    if (person.id == 0) {
//                        onAddButtonClicked(person)
//                    } else {
//                        onEditButtonClicked(person)
//                    }
                },
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius))
            ) {
                Text(
                    text = ("test"
//                        if (person.id == 0) {
//                            "Add person"
//                        } else {
//                            "Edit person"
//                        }
                    )
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
                    text = item.price,
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
    GiftManagerAppTheme(dynamicColor = false) {
        WishlistItemCard(
            WishListItem(
                id = 0,
                title = "test",
                price = "20.99",
                imageUrl = "https://m.media-amazon.com/images/I/51UnZ6EohmL._SS135_.jpg",
                itemUrl = "testURL",
                personId = 0
            )
        )
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode",
    uiMode = 33)
@Composable
fun ProfilePagePreview() {
    GiftManagerAppTheme(dynamicColor = false) {
        ProfilePage(viewModel(factory = AppViewModelProvider.Factory),{}, {}, {}, {}, modifier = Modifier)
    }
}
