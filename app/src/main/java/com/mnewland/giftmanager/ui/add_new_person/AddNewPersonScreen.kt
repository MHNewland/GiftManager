package com.mnewland.giftmanager.com.mnewland.giftmanager.ui.add_new_person


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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mnewland.giftmanager.AppViewModelProvider
import com.mnewland.giftmanager.R
import com.mnewland.giftmanager.com.mnewland.giftmanager.GiftManagerAppBar
import com.mnewland.giftmanager.com.mnewland.giftmanager.navigation.NavigationDestination
import kotlinx.coroutines.launch

object AddPersonDestination : NavigationDestination {
    override val route = "add_person"
    override val titleRes = R.string.add_person
}

@Composable
fun AddNewPersonPage(
    onBackButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
    navigateToAddedPerson: (Int) -> Unit,
    viewModel: AddNewPersonViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState = viewModel.addNewPersonUiState
    val personData = uiState.personData

    Scaffold(
        topBar = {
            GiftManagerAppBar(
                onBackButtonPressed = onBackButtonClick,
                canNavigateUp = true,
                onSettingsButtonClick = onSettingsButtonClick,
                context = LocalContext.current,
                currentScreen = stringResource(AddPersonDestination.titleRes),
                helpMessage = "Enter the person's name and click \"Add Person\" to add them to the list.\n" +
                    "It will then take you to their profile where you can fill out the rest of their information."
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
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
                        viewModel.updateUiState(personData.copy(name = it))
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
                    onClick = {
                        coroutineScope.launch {
                            val personId = viewModel.addPerson()
                            navigateToAddedPerson(personId)
                        }
                    },
                    shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius))
                ) {
                    Text(
                        text = "Add Person"
                    )
                }
            }
        }
    }
}


@Preview(name = "Light Mode")
@Preview(name = "Dark Mode",
    uiMode = 33)
@Composable
fun AddNewPersonPreview() {
    AddNewPersonPage({},{},{})
}
