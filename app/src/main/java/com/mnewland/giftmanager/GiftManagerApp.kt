package com.mnewland.giftmanager.com.mnewland.giftmanager

import android.app.AlertDialog
import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import com.mnewland.giftmanager.R
import com.mnewland.giftmanager.com.mnewland.giftmanager.navigation.GiftManagerNavGraph


@Composable
fun GiftManagerApp(navController: NavHostController = rememberNavController(), windowSize: WindowSizeClass) {
    GiftManagerNavGraph(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiftManagerAppBar(
    onBackButtonPressed: () -> Unit = {},
    canNavigateUp: Boolean,
    onSettingsButtonClick: () -> Unit,
    context: Context,
    helpMessage: String = "",
    currentScreen: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = currentScreen,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            if (canNavigateUp) {
                IconButton(onClick = onBackButtonPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = {
            val listAlertBox: AlertDialog.Builder = AlertDialog.Builder(context)
            listAlertBox
                .setTitle("Help")
                .setMessage(helpMessage)
                .setNeutralButton("OK") { dialog, which ->}
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
                    contentDescription = stringResource(R.string.preferences)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
    )
}