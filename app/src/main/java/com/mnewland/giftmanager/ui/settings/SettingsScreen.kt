package com.mnewland.giftmanager.com.mnewland.giftmanager.ui.settings

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mnewland.giftmanager.R
import com.mnewland.giftmanager.com.mnewland.giftmanager.GiftManagerAppBar
import com.mnewland.giftmanager.com.mnewland.giftmanager.navigation.NavigationDestination
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.theme.ThemeManager
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.theme.ThemeManager.THEMES

object SettingsDestination : NavigationDestination {
    override val route = "settings"
    override val titleRes = R.string.preferences
}

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun SettingsScreen(
    onBackButtonPressed: () -> Unit,
    context: Context = LocalContext.current,
    activity: Activity,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            GiftManagerAppBar(
                onBackButtonPressed = onBackButtonPressed,
                canNavigateUp = true,
                onSettingsButtonClick = { },
                context = LocalContext.current,
                currentScreen = stringResource(SettingsDestination.titleRes),
                helpMessage = "Select your theme and press \"Set Theme\" to save it.",
            )
        }
    ) { innerPadding ->
        val initialTheme = ThemeManager.getThemeName(context)
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(initialTheme)}
        Box(
            modifier = modifier.padding(innerPadding)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .selectableGroup()
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                THEMES.forEach {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (it.key == selectedOption),
                                onClick = { onOptionSelected(it.key) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                    ) {
                        RadioButton(
                            selected = (it.key == selectedOption),
                            onClick = {onOptionSelected(it.key)}
                        )
                        Text(
                            text = it.key,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                Row{
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {
                        Button(
                            onClick = {
                                ThemeManager.saveTheme(context, selectedOption)
                                ThemeManager.applyTheme(context)
                                activity.recreate()
                            }
                        ) {
                            Text(text = "Set Theme")
                        }
                    }
                }
            }
        }
    }
}



@Preview
@Composable
fun PrefTest(){
    SettingsScreen({}, activity = AppCompatActivity())
}