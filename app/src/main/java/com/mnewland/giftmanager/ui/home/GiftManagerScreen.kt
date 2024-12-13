package com.mnewland.giftmanager.com.mnewland.giftmanager.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mnewland.giftmanager.AppViewModelProvider
import com.mnewland.giftmanager.com.mnewland.giftmanager.GiftManagerAppBar
import com.mnewland.giftmanager.R
import com.mnewland.giftmanager.com.mnewland.giftmanager.navigation.NavigationDestination
import com.mnewland.giftmanager.data.person.Person

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.gift_manager
}
@Composable
fun ListLayout(
    viewModel: GiftManagerViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onSettingsButtonPressed: () -> Unit,
    onAddPersonPressed: () -> Unit,
    navigateToProfile:(Int) -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            GiftManagerAppBar(
                canNavigateUp = false,
                onSettingsButtonClick = onSettingsButtonPressed,
                context = LocalContext.current,
                currentScreen = stringResource(HomeDestination.titleRes),
                helpMessage = "To add a person, press the \"+\" in the bottom right.\n\n" +
                        "When the person's \"Purchased Item\" field is filled out, it " +
                        "will check the box next to their name and show what item was purchased.",
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddPersonPressed
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_person)
                )
            }
        }

    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            val people by viewModel.giftManagerUiState.collectAsState()
            people.personList.forEach { person ->
                PersonCardLayout(
                    person = person,
                    navigateToProfile = navigateToProfile
                )
            }
        }
    }
}


@Composable
fun PersonCardLayout(
    person: Person,
    navigateToProfile: (Int) -> Unit,
    modifier: Modifier = Modifier,
){
    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = MaterialTheme.colorScheme.error,
            disabledContainerColor = MaterialTheme.colorScheme.errorContainer
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small))
            .height(dimensionResource(R.dimen.card_image_height))
    ){
        Row(
        ) {
            Checkbox(
                checked = person.purchasedItem!="",
                enabled = false,
                onCheckedChange = null,
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = dimensionResource(R.dimen.padding_small))
            )
            CardData(
                person = person,
                itemPurchased = person.purchasedItem!="",
                onProfileButtonClicked = {navigateToProfile(it.id)}
            )
        }
    }
}

@Composable
fun CardData(
    person: Person,
    itemPurchased: Boolean,
    onProfileButtonClicked: (Person) -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = {onProfileButtonClicked(person)},
        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.errorContainer,
            disabledContainerColor = MaterialTheme.colorScheme.error),
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small))
    ){
        Column(
            modifier = modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .fillMaxSize()
                //.border(1.dp, Color.Black)
        ) {
            Row(
                modifier =
                    if (itemPurchased) {
                        modifier
                            .fillMaxWidth()
                    } else {
                        modifier
                            .fillMaxSize()

                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = person.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = (
                            if (itemPurchased) {
                                TextAlign.Start
                            } else {
                                TextAlign.Center
                            }
                    ),
                    modifier = modifier.fillMaxWidth()
                )
            }
            if(itemPurchased){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    //horizontalArrangement = Arrangement.Center,
                    modifier = modifier
                        .fillMaxSize()
                        //.border(1.dp, Color.Blue)
                ) {
                    Text(
                        text = person.purchasedItem,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}


@Preview(name = "Light Mode", showBackground = false)
@Preview(name = "Dark Mode",
    uiMode = 33, showBackground = false)
@Composable
fun PersonCardLayoutPreview(){
    PersonCardLayout(Person(), {})
}

@Preview(name = "Light Mode", showBackground = false)
@Preview(name = "Dark Mode",
    uiMode = 33, showBackground = false)
@Composable
fun ListLayoutPreview(){
    //ListLayout(PersonList.getPersonList(),{},{}, modifier = Modifier)
}

