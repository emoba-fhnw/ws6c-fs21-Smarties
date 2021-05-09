package fhnw.ws6c.theapp.ui.theme

import android.graphics.Color
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.ws6c.R

private val Roboto = FontFamily(
        Font(R.font.roboto_thin,        FontWeight.Thin),
        Font(R.font.roboto_regular,     FontWeight.Normal)
)

// Set of Material typography styles to start with
val typography = Typography(
        defaultFontFamily = Roboto,

        // App Title
        h1 = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
//                lineHeight = 117.sp,
//                letterSpacing = (-1.5).sp,
        ),
        h2 = TextStyle(
                fontSize = 60.sp,
                fontWeight = FontWeight.Light,
                lineHeight = 73.sp,
                letterSpacing = (-0.5).sp
        ),
        h3 = TextStyle(
                fontSize = 48.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 59.sp
        ),
        h4 = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 37.sp
        ),
        h5 = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 29.sp
        ),
        h6 = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 24.sp
        ),
        subtitle1 = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 24.sp,
                letterSpacing = 0.15.sp
        ),
        subtitle2 = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                letterSpacing = 0.1.sp
        ),

        // Category Cocktail name
        body1 = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
//                lineHeight = 28.sp,
//                letterSpacing = 0.15.sp,
        ),
        body2 = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp
        ),
        button = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 16.sp,
                letterSpacing = 1.25.sp
        ),
        caption = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 16.sp,
                letterSpacing = 0.4.sp
        ),
        overline = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 16.sp,
                letterSpacing = 1.sp
        )
)