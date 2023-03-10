package com.example.weatherapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val DefaultTextStyle = TextStyle(color = PrimaryTextColor)
val ExtraBigTextStyle = DefaultTextStyle.copy(fontSize = 52.sp, fontWeight = FontWeight.Medium)
val ExtraMediumTextStyle = DefaultTextStyle.copy(fontSize = 28.sp)
val MediumTextStyle = DefaultTextStyle.copy(fontSize = 20.sp)
val SmallTextStyle = DefaultTextStyle.copy(fontSize = 18.sp)
