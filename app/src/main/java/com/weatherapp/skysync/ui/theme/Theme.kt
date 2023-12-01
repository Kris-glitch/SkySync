package com.weatherapp.skysync.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView


private val skyColorScheme = lightColorScheme(
    primary = PinkDirty,
    secondary = DarkBlue,
    tertiary = OffWhite,
    onBackground = DarkBlue,
    background = LightBlue,
    onPrimary = OffYellow

)

@Composable
fun SkySyncTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = skyColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}