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
import com.mnewland.giftmanager.model.Person
import com.mnewland.giftmanager.ui.theme.GiftManagerAppTheme

@Composable
fun ProfilePage(
    person: Person = Person(),
    onValueChanged: (Person) -> Unit,
    onAddButtonClicked: (Person) -> Unit,
    onEditButtonClicked: (Person) -> Unit,
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier
            .fillMaxSize()
            //.border(width = 2.dp, Color.Red)
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        )
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
            //.border(width = 2.dp, Color.LightGray)
        )
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
                    if(person.id==0) {
                        onAddButtonClicked(person)
                    }else{
                        onEditButtonClicked(person)
                    }
                }
            ),
            onValueChange = {
                onValueChanged(
                    person.copy(purchasedItem = it)
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
                if(person.id==0) {
                    onAddButtonClicked(person)
                }else{
                    onEditButtonClicked(person)
                }
            },
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius))
        ) {
            Text(
                 text =
                    if(person.id==0){
                        "Add person"
                    }else{
                        "Edit person"
                    }
            )
        }
    }


}

@Preview(name = "Light Mode", showBackground = false)
@Preview(name = "Dark Mode",
    uiMode = 33, showBackground = false)
@Composable
fun ProfilePagePreview() {
    GiftManagerAppTheme(dynamicColor = false) {
        ProfilePage(PersonList.defaultPerson,{}, {}, {}, modifier = Modifier)
    }
}