package fhnw.ws6c.theapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val appDarkColors = Colors(
    //Background colors
    primary          = Color(0xFFBB86FC), //lila
    primaryVariant   = Color(0xFF3700B3),
    secondary        = Color(0xFF5E487A), //dunkellila
    secondaryVariant = Color(0xFF03DAC6),
    background       = Color(0xFFFFFFFF), //black
    surface          = Color(0xFF121212),
    error            = Color(0xFFCF6679),
    
    //Typography and icon colors
    onPrimary        = Color.Black,
    onSecondary      = Color.Gray,
    onBackground     = Color.White,
    onSurface        = Color.White,
    onError          = Color.Black,
    
    isLight = false
)

private val appLightColors = Colors(
    //Background colors
        primary          = Color(0xFF04275C),
        primaryVariant   = Color(0xFF465D81),
        secondary        = Color(0xFF7C92B4),
        
        secondaryVariant = Color(0xFFB7C3D3),
        background       = Color.White,
        surface          = Color.White,
        error            = Color(0xFFB00020),

    //Typography and icon colors
        onPrimary        = Color.White, //Color.White
        onSecondary      = Color.Gray,
        onBackground     = Color.Black,
        onSurface        = Color.Black,
        onError          = Color.White,
        
        isLight = true
)

@Composable
fun AppTheme(darkTheme: Boolean, content: @Composable() () -> Unit) {
    MaterialTheme(
            colors     = if (darkTheme) appDarkColors else appLightColors,
            typography = typography,
            shapes     = shapes,
            content    = content
    )
}