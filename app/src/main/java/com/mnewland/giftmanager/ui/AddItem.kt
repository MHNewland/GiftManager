package com.mnewland.giftmanager.ui


import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.mnewland.giftmanager.R
import com.mnewland.giftmanager.data.PersonList
import com.mnewland.giftmanager.data.Wishlist
import com.mnewland.giftmanager.model.Person
import com.mnewland.giftmanager.model.WishlistItem
import com.mnewland.giftmanager.ui.theme.GiftManagerAppTheme

@Composable
fun AddItem(
    item: WishlistItem,
    onValueChanged: (WishlistItem) -> Unit,
    onAddButtonClicked: (WishlistItem) -> Unit,
    onEditButtonClicked: (WishlistItem) -> Unit,
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier
            .fillMaxSize()
            //.border(width = 2.dp, Color.Red)
    ) {
        TextField(
            value = item.title,
            label = {
                    Text(
                        text = stringResource(R.string.name)
                    )
            },
            onValueChange = {
                onValueChanged(
                    item.copy(title = it)
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        )
        TextField(
            value = item.itemUrl,
            label = {
                Text(
                    text = stringResource(R.string.list_link)
                )
            },
            onValueChange = {
                onValueChanged(
                    item.copy(itemUrl = it)
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
            //.border(width = 2.dp, Color.LightGray)
        )
        TextField(
            value = item.price,
            label = {
                Text(
                    text = stringResource(R.string.purchaced_item)
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    onAddButtonClicked(item)
                }
            ),
            onValueChange = {
                onValueChanged(
                    item.copy(price = it)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
            //.border(width = 2.dp, Color.LightGray)
        )

    }
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Button(
            onClick = {
                onAddButtonClicked(item)
            },
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius))
        ) {
            Text(
                 text = "Add person"
            )
        }
    }


}

@Preview(name = "Light Mode", showBackground = false)
@Preview(name = "Dark Mode",
    uiMode = 33, showBackground = false)
@Composable
fun AddItemPreview() {
    GiftManagerAppTheme(dynamicColor = false) {
        AddItem(WishlistItem(),{}, {}, {}, modifier = Modifier)
    }
}