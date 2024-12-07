package com.mnewland.giftmanager

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mnewland.giftmanager.model.Person
import com.mnewland.giftmanager.ui.ListLayout
import com.mnewland.giftmanager.ui.PreferencesScreen
import com.mnewland.giftmanager.ui.ProfilePage
import com.mnewland.giftmanager.utils.GiftManagerContentType
import com.mnewland.giftmanager.view_models.GiftManagerViewModel


enum class GiftManagerScreen(@StringRes val title: Int) {
    GiftManager(title = R.string.gift_manager),
    Preferences(title = R.string.preferences),
    Profile(title = R.string.profile),
}


@Composable
fun GiftManagerApp(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier
){
    val contentType: GiftManagerContentType

    //not implemented yet
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
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GiftManagerScreen.valueOf(
        backStackEntry?.destination?.route ?: GiftManagerScreen.GiftManager.name
    )

    Scaffold(
        topBar = {
            GiftManagerAppBar(
                onBackButtonClick = { navController.navigateUp() },
                onSettingsButtonClick = { navController.navigate(GiftManagerScreen.Preferences.name) },
                currentScreen = currentScreen,
                context = LocalContext.current
            )
        },
        floatingActionButton = {
            if(currentScreen.title == R.string.gift_manager) {
                FloatingActionButton(
                    onClick = {
                        viewModel.updateCurrentPerson(Person())
                        navController.navigate(GiftManagerScreen.Profile.name)
                    },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_medium))

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_person),
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = GiftManagerScreen.GiftManager.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = GiftManagerScreen.GiftManager.name) {
                ListLayout(
                    uiState.personList,
                    onCheckedChange = {
                        viewModel.updateCurrentPerson(it)
                        navController.navigate(GiftManagerScreen.Profile.name)
                    },
                    onProfileButtonClicked = {
                        viewModel.updateCurrentPerson(it)
                        navController.navigate(GiftManagerScreen.Profile.name)
                    },
                    modifier = modifier.padding(innerPadding)
                )
            }
            composable(route = GiftManagerScreen.Preferences.name){
                PreferencesScreen(
                )
            }
            composable(route = GiftManagerScreen.Profile.name) {
                ProfilePage(
                    uiState.currentPerson,
                    onValueChanged = {
                        viewModel.updateCurrentPerson(it)
                    },
                    onAddButtonClicked = {
                        viewModel.addPerson(it)
                        navController.navigateUp()
                    },
                    onEditButtonClicked = {
                        viewModel.updatePersonData(it)
                        navController.navigateUp()
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

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiftManagerAppBar(
    onBackButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
    context: Context,
    currentScreen: GiftManagerScreen,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(currentScreen.title),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = if (currentScreen.title != R.string.gift_manager) {
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
            when(currentScreen.title) {
                R.string.gift_manager -> {
                    listAlertBox
                        .setMessage(
                            "To add a person, press the \"+\" in the bottom right.\n\n" +
                                    "When the person's \"Purchased Item\" field is filled out, it " +
                                    "will check the box next to their name and show what item was purchased."
                        )
                }
                R.string.profile -> {
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
            IconButton(
                onClick = onSettingsButtonClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
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