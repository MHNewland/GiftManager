package com.mnewland.giftmanager.ui

<<<<<<< HEAD
import android.app.AlertDialog
import android.content.Context
=======
>>>>>>> AmazonList
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
<<<<<<< HEAD
=======
import androidx.compose.foundation.layout.safeContentPadding
>>>>>>> AmazonList
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.compose.ui.platform.LocalContext
=======
>>>>>>> AmazonList
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mnewland.giftmanager.R
import com.mnewland.giftmanager.utils.GiftManagerContentType
import com.mnewland.giftmanager.data.PersonList
import com.mnewland.giftmanager.model.Person
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mnewland.giftmanager.ui.theme.GiftManagerAppTheme
import com.mnewland.giftmanager.view_models.GiftManagerViewModel


@Composable
fun GiftManagerApp(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier
){
    val contentType: GiftManagerContentType

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            contentType = GiftManagerContentType.ListOnly
        }
        WindowWidthSizeClass.Medium -> {
            contentType = GiftManagerContentType.ListOnly
        }
        WindowWidthSizeClass.Expanded -> {
            contentType = GiftManagerContentType.ListAndDetail
        }
        else -> {
            contentType = GiftManagerContentType.ListOnly
        }
    }

    val viewModel: GiftManagerViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            GiftManagerAppBar(
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { viewModel.navigateToListPage() },
                context = LocalContext.current
            )
        },
        floatingActionButton = {
            if(uiState.isShowingListPage) {
                FloatingActionButton(
                    onClick = {
                        viewModel.updateCurrentPerson(Person())
                        viewModel.navigateToProfilePage()
                    },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_person),
                    )
                }
            }
        }
    ) { innerPadding ->
        if (uiState.isShowingListPage) {
            ListLayout(
                uiState.personList,
                onCheckedChange = {
                    viewModel.updateCurrentPerson(it)
                    viewModel.navigateToProfilePage()
                },
                onProfileButtonClicked = {
                    viewModel.updateCurrentPerson(it)
                    viewModel.navigateToProfilePage()
                },
                modifier = modifier.padding(innerPadding)
            )
        }else{
            ProfilePage(
                uiState.currentPerson,
                onValueChanged = {
                    viewModel.updateCurrentPerson(it)
                },
                onAddButtonClicked = {
                    viewModel.addPerson(it)
                    viewModel.navigateToListPage()
                },
                onEditButtonClicked = {
                    viewModel.updatePersonData(it)
                    viewModel.navigateToListPage()
                },
                onSyncButtonClicked = {
                    viewModel.updatePersonData(it)
                },
                modifier = modifier
                    .padding(innerPadding)
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiftManagerAppBar(
    onBackButtonClick: () -> Unit,
    context: Context,
    isShowingListPage: Boolean,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = "Gift List",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = if (!isShowingListPage) {
            {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        } else {
            { Box {} }
        },
        actions = {
            val listAlertBox: AlertDialog.Builder = AlertDialog.Builder(context)
            listAlertBox
                .setTitle("Help")
                .setNeutralButton("OK") { dialog, which ->
                }
            if(isShowingListPage) {

                listAlertBox
                    .setMessage(
                        "To add a person, press the \"+\" in the bottom right.\n\n" +
                                "When the person's \"Purchased Item\" field is filled out, it " +
                                "will check the box next to their name and show what item was purchased."
                    )
            }else{
                listAlertBox
                    .setMessage(
                        "You can set the person's information here.\n\n If anything is placed in the" +
                                "\"Purchased Item\" field, it will show a check mark and display " +
                                "what's entered on the main screen.\n\n" +
                                "Currently, the app only syncs with Amazon wish lists that are public. " +
                                "To sync to an amazon list, copy and paste the URL or the ID for the list and click " +
                                "the sync button.\n\n" +
                                "To find the ID, look for the string of characters after \"www.amazon.com/hz/wishlist/ls/\"."
                    )
            }
            IconButton(
                onClick = {
                    val dialog: AlertDialog = listAlertBox.create()
                    dialog.show()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.help_outline),
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
    )
}

@Composable
fun PersonCardLayout(
    person: Person,
    onCheckedChange: (Person) -> Unit,
    onProfileButtonClicked: (Person) -> Unit,
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
                onCheckedChange = {
                    onCheckedChange(person)
                },
                modifier = modifier
                    .align(Alignment.CenterVertically)
            )
            CardData(
                person = person,
                itemPurchased = person.purchasedItem!="",
                onProfileButtonClicked = {onProfileButtonClicked(person)}
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

@Composable
fun ListLayout(
    people: List<Person>,
    onCheckedChange: (Person) -> Unit,
    onProfileButtonClicked: (Person) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        people.forEach { person ->
            PersonCardLayout(
                person = person,
                onCheckedChange = onCheckedChange,
                onProfileButtonClicked = onProfileButtonClicked
            )
        }
    }
}


@Preview(name = "Light Mode", showBackground = false)
@Preview(name = "Dark Mode",
    uiMode = 33, showBackground = false)
@Composable
fun PersonCardLayoutPreview(){
    GiftManagerAppTheme(dynamicColor = false) {
        PersonCardLayout(PersonList.defaultPerson,{},{}, modifier = Modifier)
    }
}

@Preview(name = "Light Mode", showBackground = false)
@Preview(name = "Dark Mode",
    uiMode = 33, showBackground = false)
@Composable
fun ListLayoutPreview(){
    GiftManagerAppTheme(dynamicColor = false){
        ListLayout(PersonList.getPersonList(),{},{}, modifier = Modifier)
    }
}

