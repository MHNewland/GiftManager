package com.mnewland.giftmanager.com.mnewland.giftmanager.ui.theme

import android.content.Context
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.mnewland.giftmanager.R
import com.mnewland.giftmanager.ui.theme.backgroundDark
import com.mnewland.giftmanager.ui.theme.backgroundDarkHighContrast
import com.mnewland.giftmanager.ui.theme.backgroundDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.backgroundLight
import com.mnewland.giftmanager.ui.theme.backgroundLightHighContrast
import com.mnewland.giftmanager.ui.theme.backgroundLightMediumContrast
import com.mnewland.giftmanager.ui.theme.errorContainerDark
import com.mnewland.giftmanager.ui.theme.errorContainerDarkHighContrast
import com.mnewland.giftmanager.ui.theme.errorContainerDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.errorContainerLight
import com.mnewland.giftmanager.ui.theme.errorContainerLightHighContrast
import com.mnewland.giftmanager.ui.theme.errorContainerLightMediumContrast
import com.mnewland.giftmanager.ui.theme.errorDark
import com.mnewland.giftmanager.ui.theme.errorDarkHighContrast
import com.mnewland.giftmanager.ui.theme.errorDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.errorLight
import com.mnewland.giftmanager.ui.theme.errorLightHighContrast
import com.mnewland.giftmanager.ui.theme.errorLightMediumContrast
import com.mnewland.giftmanager.ui.theme.inverseOnSurfaceDark
import com.mnewland.giftmanager.ui.theme.inverseOnSurfaceDarkHighContrast
import com.mnewland.giftmanager.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.inverseOnSurfaceLight
import com.mnewland.giftmanager.ui.theme.inverseOnSurfaceLightHighContrast
import com.mnewland.giftmanager.ui.theme.inverseOnSurfaceLightMediumContrast
import com.mnewland.giftmanager.ui.theme.inversePrimaryDark
import com.mnewland.giftmanager.ui.theme.inversePrimaryDarkHighContrast
import com.mnewland.giftmanager.ui.theme.inversePrimaryDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.inversePrimaryLight
import com.mnewland.giftmanager.ui.theme.inversePrimaryLightHighContrast
import com.mnewland.giftmanager.ui.theme.inversePrimaryLightMediumContrast
import com.mnewland.giftmanager.ui.theme.inverseSurfaceDark
import com.mnewland.giftmanager.ui.theme.inverseSurfaceDarkHighContrast
import com.mnewland.giftmanager.ui.theme.inverseSurfaceDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.inverseSurfaceLight
import com.mnewland.giftmanager.ui.theme.inverseSurfaceLightHighContrast
import com.mnewland.giftmanager.ui.theme.inverseSurfaceLightMediumContrast
import com.mnewland.giftmanager.ui.theme.onBackgroundDark
import com.mnewland.giftmanager.ui.theme.onBackgroundDarkHighContrast
import com.mnewland.giftmanager.ui.theme.onBackgroundDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.onBackgroundLight
import com.mnewland.giftmanager.ui.theme.onBackgroundLightHighContrast
import com.mnewland.giftmanager.ui.theme.onBackgroundLightMediumContrast
import com.mnewland.giftmanager.ui.theme.onErrorContainerDark
import com.mnewland.giftmanager.ui.theme.onErrorContainerDarkHighContrast
import com.mnewland.giftmanager.ui.theme.onErrorContainerDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.onErrorContainerLight
import com.mnewland.giftmanager.ui.theme.onErrorContainerLightHighContrast
import com.mnewland.giftmanager.ui.theme.onErrorContainerLightMediumContrast
import com.mnewland.giftmanager.ui.theme.onErrorDark
import com.mnewland.giftmanager.ui.theme.onErrorDarkHighContrast
import com.mnewland.giftmanager.ui.theme.onErrorDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.onErrorLight
import com.mnewland.giftmanager.ui.theme.onErrorLightHighContrast
import com.mnewland.giftmanager.ui.theme.onErrorLightMediumContrast
import com.mnewland.giftmanager.ui.theme.onPrimaryContainerDark
import com.mnewland.giftmanager.ui.theme.onPrimaryContainerDarkHighContrast
import com.mnewland.giftmanager.ui.theme.onPrimaryContainerDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.onPrimaryContainerLight
import com.mnewland.giftmanager.ui.theme.onPrimaryContainerLightHighContrast
import com.mnewland.giftmanager.ui.theme.onPrimaryContainerLightMediumContrast
import com.mnewland.giftmanager.ui.theme.onPrimaryDark
import com.mnewland.giftmanager.ui.theme.onPrimaryDarkHighContrast
import com.mnewland.giftmanager.ui.theme.onPrimaryDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.onPrimaryLight
import com.mnewland.giftmanager.ui.theme.onPrimaryLightHighContrast
import com.mnewland.giftmanager.ui.theme.onPrimaryLightMediumContrast
import com.mnewland.giftmanager.ui.theme.onSecondaryContainerDark
import com.mnewland.giftmanager.ui.theme.onSecondaryContainerDarkHighContrast
import com.mnewland.giftmanager.ui.theme.onSecondaryContainerDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.onSecondaryContainerLight
import com.mnewland.giftmanager.ui.theme.onSecondaryContainerLightHighContrast
import com.mnewland.giftmanager.ui.theme.onSecondaryContainerLightMediumContrast
import com.mnewland.giftmanager.ui.theme.onSecondaryDark
import com.mnewland.giftmanager.ui.theme.onSecondaryDarkHighContrast
import com.mnewland.giftmanager.ui.theme.onSecondaryDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.onSecondaryLight
import com.mnewland.giftmanager.ui.theme.onSecondaryLightHighContrast
import com.mnewland.giftmanager.ui.theme.onSecondaryLightMediumContrast
import com.mnewland.giftmanager.ui.theme.onSurfaceDark
import com.mnewland.giftmanager.ui.theme.onSurfaceDarkHighContrast
import com.mnewland.giftmanager.ui.theme.onSurfaceDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.onSurfaceLight
import com.mnewland.giftmanager.ui.theme.onSurfaceLightHighContrast
import com.mnewland.giftmanager.ui.theme.onSurfaceLightMediumContrast
import com.mnewland.giftmanager.ui.theme.onSurfaceVariantDark
import com.mnewland.giftmanager.ui.theme.onSurfaceVariantDarkHighContrast
import com.mnewland.giftmanager.ui.theme.onSurfaceVariantDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.onSurfaceVariantLight
import com.mnewland.giftmanager.ui.theme.onSurfaceVariantLightHighContrast
import com.mnewland.giftmanager.ui.theme.onSurfaceVariantLightMediumContrast
import com.mnewland.giftmanager.ui.theme.onTertiaryContainerDark
import com.mnewland.giftmanager.ui.theme.onTertiaryContainerDarkHighContrast
import com.mnewland.giftmanager.ui.theme.onTertiaryContainerDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.onTertiaryContainerLight
import com.mnewland.giftmanager.ui.theme.onTertiaryContainerLightHighContrast
import com.mnewland.giftmanager.ui.theme.onTertiaryContainerLightMediumContrast
import com.mnewland.giftmanager.ui.theme.onTertiaryDark
import com.mnewland.giftmanager.ui.theme.onTertiaryDarkHighContrast
import com.mnewland.giftmanager.ui.theme.onTertiaryDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.onTertiaryLight
import com.mnewland.giftmanager.ui.theme.onTertiaryLightHighContrast
import com.mnewland.giftmanager.ui.theme.onTertiaryLightMediumContrast
import com.mnewland.giftmanager.ui.theme.outlineDark
import com.mnewland.giftmanager.ui.theme.outlineDarkHighContrast
import com.mnewland.giftmanager.ui.theme.outlineDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.outlineLight
import com.mnewland.giftmanager.ui.theme.outlineLightHighContrast
import com.mnewland.giftmanager.ui.theme.outlineLightMediumContrast
import com.mnewland.giftmanager.ui.theme.outlineVariantDark
import com.mnewland.giftmanager.ui.theme.outlineVariantDarkHighContrast
import com.mnewland.giftmanager.ui.theme.outlineVariantDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.outlineVariantLight
import com.mnewland.giftmanager.ui.theme.outlineVariantLightHighContrast
import com.mnewland.giftmanager.ui.theme.outlineVariantLightMediumContrast
import com.mnewland.giftmanager.ui.theme.primaryContainerDark
import com.mnewland.giftmanager.ui.theme.primaryContainerDarkHighContrast
import com.mnewland.giftmanager.ui.theme.primaryContainerDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.primaryContainerLight
import com.mnewland.giftmanager.ui.theme.primaryContainerLightHighContrast
import com.mnewland.giftmanager.ui.theme.primaryContainerLightMediumContrast
import com.mnewland.giftmanager.ui.theme.primaryDark
import com.mnewland.giftmanager.ui.theme.primaryDarkHighContrast
import com.mnewland.giftmanager.ui.theme.primaryDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.primaryLight
import com.mnewland.giftmanager.ui.theme.primaryLightHighContrast
import com.mnewland.giftmanager.ui.theme.primaryLightMediumContrast
import com.mnewland.giftmanager.ui.theme.scrimDark
import com.mnewland.giftmanager.ui.theme.scrimDarkHighContrast
import com.mnewland.giftmanager.ui.theme.scrimDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.scrimLight
import com.mnewland.giftmanager.ui.theme.scrimLightHighContrast
import com.mnewland.giftmanager.ui.theme.scrimLightMediumContrast
import com.mnewland.giftmanager.ui.theme.secondaryContainerDark
import com.mnewland.giftmanager.ui.theme.secondaryContainerDarkHighContrast
import com.mnewland.giftmanager.ui.theme.secondaryContainerDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.secondaryContainerLight
import com.mnewland.giftmanager.ui.theme.secondaryContainerLightHighContrast
import com.mnewland.giftmanager.ui.theme.secondaryContainerLightMediumContrast
import com.mnewland.giftmanager.ui.theme.secondaryDark
import com.mnewland.giftmanager.ui.theme.secondaryDarkHighContrast
import com.mnewland.giftmanager.ui.theme.secondaryDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.secondaryLight
import com.mnewland.giftmanager.ui.theme.secondaryLightHighContrast
import com.mnewland.giftmanager.ui.theme.secondaryLightMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceBrightDark
import com.mnewland.giftmanager.ui.theme.surfaceBrightDarkHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceBrightDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceBrightLight
import com.mnewland.giftmanager.ui.theme.surfaceBrightLightHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceBrightLightMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerDark
import com.mnewland.giftmanager.ui.theme.surfaceContainerDarkHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerHighDark
import com.mnewland.giftmanager.ui.theme.surfaceContainerHighDarkHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerHighDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerHighLight
import com.mnewland.giftmanager.ui.theme.surfaceContainerHighLightHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerHighLightMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerHighestDark
import com.mnewland.giftmanager.ui.theme.surfaceContainerHighestDarkHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerHighestLight
import com.mnewland.giftmanager.ui.theme.surfaceContainerHighestLightHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerHighestLightMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerLight
import com.mnewland.giftmanager.ui.theme.surfaceContainerLightHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerLightMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerLowDark
import com.mnewland.giftmanager.ui.theme.surfaceContainerLowDarkHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerLowDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerLowLight
import com.mnewland.giftmanager.ui.theme.surfaceContainerLowLightHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerLowLightMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerLowestDark
import com.mnewland.giftmanager.ui.theme.surfaceContainerLowestDarkHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerLowestLight
import com.mnewland.giftmanager.ui.theme.surfaceContainerLowestLightHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceContainerLowestLightMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceDark
import com.mnewland.giftmanager.ui.theme.surfaceDarkHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceDimDark
import com.mnewland.giftmanager.ui.theme.surfaceDimDarkHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceDimDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceDimLight
import com.mnewland.giftmanager.ui.theme.surfaceDimLightHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceDimLightMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceLight
import com.mnewland.giftmanager.ui.theme.surfaceLightHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceLightMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceVariantDark
import com.mnewland.giftmanager.ui.theme.surfaceVariantDarkHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceVariantDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.surfaceVariantLight
import com.mnewland.giftmanager.ui.theme.surfaceVariantLightHighContrast
import com.mnewland.giftmanager.ui.theme.surfaceVariantLightMediumContrast
import com.mnewland.giftmanager.ui.theme.tertiaryContainerDark
import com.mnewland.giftmanager.ui.theme.tertiaryContainerDarkHighContrast
import com.mnewland.giftmanager.ui.theme.tertiaryContainerDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.tertiaryContainerLight
import com.mnewland.giftmanager.ui.theme.tertiaryContainerLightHighContrast
import com.mnewland.giftmanager.ui.theme.tertiaryContainerLightMediumContrast
import com.mnewland.giftmanager.ui.theme.tertiaryDark
import com.mnewland.giftmanager.ui.theme.tertiaryDarkHighContrast
import com.mnewland.giftmanager.ui.theme.tertiaryDarkMediumContrast
import com.mnewland.giftmanager.ui.theme.tertiaryLight
import com.mnewland.giftmanager.ui.theme.tertiaryLightHighContrast
import com.mnewland.giftmanager.ui.theme.tertiaryLightMediumContrast

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

object ThemeManager {
    private const val PREF_NAME = "theme_pref"
    private const val KEY_THEME = "key_theme"
    val THEMES: Map<String, Int> = mapOf(
        Pair("Light", R.style.GiftManagerTheme_Light),
        Pair("Dark", R.style.GiftManagerTheme_Dark),
//        Pair("MediumContrastLight", R.style.GiftManagerTheme_MediumContrastLight),
//        Pair("HighContrastLight", R.style.GiftManagerTheme_HighContrastLight),
//        Pair("MediumContrastDark", R.style.GiftManagerTheme_MediumContrastDark),
//        Pair("HighContrastDark", R.style.GiftManagerTheme_HighContrastDark)
    )

    fun applyTheme(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val theme = sharedPreferences.getString(KEY_THEME, "Light")
        context.setTheme(THEMES[theme] ?: R.style.GiftManagerTheme_Light)
    }

    fun saveTheme(context: Context, theme: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(KEY_THEME, theme)
            apply()
        }
    }

    fun getTheme(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_THEME, "Light") ?: "Light"
    }

    fun getColorScheme(context: Context, darkTheme: Boolean): ColorScheme {
        val theme = getTheme(context)
        return when (theme) {
            "Dark" -> darkScheme
//            "MediumContrastLight" -> lightScheme
//            "HighContrastLight" -> lightScheme
//            "MediumContrastDark" -> darkScheme
//            "HighContrastDark" -> darkScheme
            else -> if (darkTheme) darkScheme else lightScheme
        }
    }

    fun getThemeName(context: Context): String{
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val theme = sharedPreferences.getString(KEY_THEME, "error")
        return theme?:"error"
    }
}