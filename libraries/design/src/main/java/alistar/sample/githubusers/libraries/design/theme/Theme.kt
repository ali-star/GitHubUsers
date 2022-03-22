package alistar.sample.githubusers.libraries.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = PrimaryDark,
    secondary = SecondaryDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    error = Red40,
    onError = Red
)

private val LightColorPalette = lightColors(
    primary = PrimaryLight,
    secondary = SecondaryLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    error = Red40,
    onError = Red,
    surface = SurfaceLight,
    onSecondary = OnBackgroundLight
)

@Composable
fun GitHubUsersTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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
