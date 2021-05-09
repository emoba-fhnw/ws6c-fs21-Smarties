package fhnw.ws6c.theapp.ui.theme

import androidx.compose.ui.graphics.Color

enum class MyColors (val light_color : Color, val dark_color : Color) {
    InstructionColor(getColor("#D8D7D7"), getColor("#FBFBFB")),
    Text(getColor("#7EEBF5"), getColor("#FBFBFB"))
}


private fun getColor(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}