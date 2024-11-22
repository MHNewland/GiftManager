package com.mnewland.giftmanager.ui


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mnewland.giftmanager.R
import com.mnewland.giftmanager.data.PersonList
import com.mnewland.giftmanager.model.Person
import com.mnewland.giftmanager.model.WishlistItem
import com.mnewland.giftmanager.network.amazonParser
import com.mnewland.giftmanager.ui.theme.GiftManagerAppTheme
import kotlinx.coroutines.launch

@Composable
fun ProfilePage(
    person: Person = Person(),
    onValueChanged: (Person) -> Unit,
    onAddButtonClicked: (Person) -> Unit,
    onEditButtonClicked: (Person) -> Unit,
    onSyncButtonClicked: (Person) -> Unit,
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxSize()
            .border(width = 2.dp, Color.Red)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            TextField(
                value = person.name,
                label = {
                    Text(
                        text = stringResource(R.string.name)
                    )
                },
                onValueChange = {
                    onValueChanged(
                        person.copy(name = it)
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = modifier
                    .fillMaxWidth()
            )
        }
        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        ){
            TextField(
                value = person.purchasedItem,
                label = {
                    Text(
                        text = stringResource(R.string.purchaced_item)
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (person.id == 0) {
                            onAddButtonClicked(person)
                        } else {
                            onEditButtonClicked(person)
                        }
                    }
                ),
                onValueChange = {
                    onValueChanged(
                        person.copy(purchasedItem = it)
                    )
                },
                modifier = modifier
                    .fillMaxWidth()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .border(2.dp, Color.LightGray)
        ) {
            TextField(
                value = person.listLink,
                label = {
                    Text(
                        text = stringResource(R.string.list_link)
                    )
                },
                onValueChange = {
                    onValueChanged(
                        person.copy(listLink = it)
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = modifier
                    .weight(1f)
                    //.padding(dimensionResource(R.dimen.padding_medium))
                //.border(width = 2.dp, Color.LightGray)
            )
            Button(
                onClick = {
                    coroutineScope.launch {
                        val updatedPerson = person.copy(wishList = amazonParser(person.listLink))
                        /*PersonList in the viewmodel is being updated
                         but viewmodel for the person is not
                         */
                        onSyncButtonClicked(updatedPerson)
                    }

                },
                modifier = modifier
                    .width(100.dp)
                    //.padding(dimensionResource(R.dimen.padding_small))
                    .border(2.dp, Color.Yellow)
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_sync_24),
                    contentDescription = stringResource(R.string.back_button)
                )

            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
                .defaultMinSize(minHeight = 100.dp)
                .border(2.dp, Color.Cyan)
        ) {
            if(person.wishList.size >0)
                ItemList(person.wishList, modifier)
            else
                Text(
                    text = "No items found"
                )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .border(2.dp, Color.Magenta)
        ) {
            Button(
                onClick = {
                    if (person.id == 0) {
                        onAddButtonClicked(person)
                    } else {
                        onEditButtonClicked(person)
                    }
                },
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius))
            ) {
                Text(
                    text = (
                        if (person.id == 0) {
                            "Add person"
                        } else {
                            "Edit person"
                        }
                    )
                )
            }
        }
    }
}

@Composable
fun ItemList(
    itemList: List<WishlistItem>,
    modifier: Modifier = Modifier
){
    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .border(2.dp, Color.Green)
    ){

        items(itemList){ item ->
            WishlistItemCard(item, modifier)
        }
    }
}

@Composable
fun WishlistItemCard(
    item: WishlistItem,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth()
    ){
        Column(
            modifier = modifier.fillMaxHeight()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .listener(
                        onStart = { request -> Log.d("Coil", "Image request started: $request") },
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
                    .border(2.dp, Color.Blue)
            )
        }
        Column() {
            Text(
                text = item.title
            )
            Text(
                text = item.price
            )
            Text(
                text= item.itemUrl
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode",
    uiMode = 33)
@Composable
fun ProfilePagePreview() {
    GiftManagerAppTheme(dynamicColor = false) {
        ProfilePage(PersonList.defaultPerson,{}, {}, {}, {}, modifier = Modifier)
    }
}