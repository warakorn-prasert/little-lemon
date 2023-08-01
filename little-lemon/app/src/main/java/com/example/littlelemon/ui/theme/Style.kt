package com.example.littlelemon.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

object LittleLemonTextStyle {
    val displayTitle = TextStyle(
        fontSize = 64.sp,
        fontFamily = FontFamily(Font(R.font.markazitext_medium))
    )

    val subtitle = TextStyle(
        fontSize = 40.sp,
        fontFamily = FontFamily(Font(R.font.markazitext_regular))
    )

    val leadText = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.karla_medium))
    )

    val sectionTitle = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.karla_extrabold))
    )

    val sectionCategories = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.karla_extrabold))
    )

    val cardTitle = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.karla_bold))
    )

    val paragraphText = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.karla_regular))
    )
}