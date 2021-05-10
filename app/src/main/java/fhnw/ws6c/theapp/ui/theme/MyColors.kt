package fhnw.ws6c.theapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

enum class MyColors(val light_color: Brush, val dark_color: Brush) {
    Background(         Brush.verticalGradient(colors = listOf(getColor("#A06CF4"), getColor("#1957B2"))),  Brush.verticalGradient(colors = listOf(getColor("#111010"), getColor("#111010")))),
    Borders(            Brush.verticalGradient(colors = listOf(getColor("#FBFBFB"), getColor("#FBFBFB"))),  Brush.horizontalGradient(colors = listOf( Color(0xFFFF00F5), Color(0xFF95A5F5)))),
    InstructionColor(   Brush.verticalGradient(colors = listOf(getColor("#D8D7D7"), getColor("#D8D7D7"))),  Brush.horizontalGradient(colors = listOf( getColor("#FBFBFB"), getColor("#FBFBFB")))),
    Text(               Brush.verticalGradient(colors = listOf(getColor("#FBFBFB"), getColor("#FBFBFB"))),  Brush.horizontalGradient(colors = listOf( getColor("#FBFBFB"), getColor("#FBFBFB"))))
}

private fun getColor(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}