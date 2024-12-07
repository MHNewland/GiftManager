package com.mnewland.giftmanager.ui

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.mnewland.giftmanager.R
import kotlin.random.Random
import kotlin.random.nextInt


@OptIn(ExperimentalStdlibApi::class)
@Composable
fun PreferencesScreen(
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier
) {
    // Define a mutable state for the background color
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    val defaultColor = colorResource(R.color.test)
    val storedColor = Color(sharedPreferences.getInt("test", Color.White.toArgb()))
    val backgroundColor = remember { mutableStateOf(storedColor) }

    Column(
        modifier
            .fillMaxSize()
            .background(color = backgroundColor.value),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {

                // Generate a random color
                val newColor = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

                // Store the color in SharedPreferences
                Log.d("prefs before", sharedPreferences.getInt("test", -1).toHexString())

                // Update the state with the new color
                backgroundColor.value = newColor

                val editor = sharedPreferences.edit()
                editor.putInt("test", newColor.toArgb())
                editor.apply()

                Log.d("prefs after", sharedPreferences.getInt("test", -1).toHexString())
            }
        ) {
            Text(text = "Press me")
        }
        Button(
            onClick = {

                // Generate a random color
                val newColor = defaultColor

                // Store the color in SharedPreferences
                Log.d("prefs before", sharedPreferences.getInt("test", -1).toHexString())

                // Update the state with the new color
                backgroundColor.value = newColor

                val editor = sharedPreferences.edit()
                editor.putInt("test", newColor.toArgb())
                editor.apply()

                Log.d("prefs after", sharedPreferences.getInt("test", -1).toHexString())
            },
            modifier.padding()
        ) {
            Text(text = "Load Default Color")
        }
    }

}

@Preview
@Composable
fun PrefTest(){
    PreferencesScreen()
}