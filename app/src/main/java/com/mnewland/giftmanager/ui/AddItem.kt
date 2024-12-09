package com.mnewland.giftmanager.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.mnewland.giftmanager.R
import com.mnewland.giftmanager.data.wish_list.WishListItem
import com.mnewland.giftmanager.ui.theme.GiftManagerAppTheme

@Composable
fun AddItem(
    item: WishListItem,
    onValueChanged: (WishListItem) -> Unit,
    onAddButtonClicked: (WishListItem) -> Unit,
    onEditButtonClicked: (WishListItem) -> Unit,
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
            value = "$"+item.price,
            label = {
                Text(
                    text = stringResource(R.string.price)
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done),
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
        //AddItem(WishListItem(),{}, {}, {}, modifier = Modifier)
    }
}