package com.mnewland.giftmanager

import android.app.Activity
import android.content.res.Resources.Theme
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.platform.LocalContext
import com.mnewland.giftmanager.com.mnewland.giftmanager.GiftManagerApp
import com.mnewland.giftmanager.com.mnewland.giftmanager.ui.theme.ThemeManager
import com.mnewland.giftmanager.ui.theme.GiftManagerAppTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Theme", theme.toString())
        ThemeManager.applyTheme(this)
        Log.d("Theme", theme.toString())
        super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                GiftManagerAppTheme(dynamicColor = false) {
                    Surface {
                        GiftManagerApp(
                            windowSize = calculateWindowSizeClass(LocalContext.current as Activity),
                            activity = LocalContext.current as Activity
                        )
                    }
                }
            }

    }
}