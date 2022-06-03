package com.example.productme.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = darkBlue100,
    primaryVariant = green,
    secondary = orange,
    background = darkBlue,
    surface = darkBlue,
    onSurface = white,
    onBackground = white,
    onPrimary = white,
    onSecondary = white

)

private val LightColorPalette = lightColors(
    primary = whiteSmoke,
    primaryVariant = green,
    secondary = orange,
    background = white,
    surface = white,
    onSurface = black,
    onBackground = black,
    onPrimary = black,
    onSecondary = white

)

@Composable
fun ProductmeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}