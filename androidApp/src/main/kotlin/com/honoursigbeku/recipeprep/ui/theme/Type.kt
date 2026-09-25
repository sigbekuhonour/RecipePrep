package com.honoursigbeku.recipeprep.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.honoursigbeku.recipeprep.R


val RecipeAppFontFamily = FontFamily(
    // Thin
    Font(resId = R.font.montserrat_thin, weight = FontWeight.Thin),
    Font(resId = R.font.montserrat_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic),

    // ExtraLight
    Font(resId = R.font.montserrat_extralight, weight = FontWeight.ExtraLight),
    Font(resId = R.font.montserrat_extra_light_italic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),

    // Light
    Font(resId = R.font.montserrat_light, weight = FontWeight.Light),
    Font(resId = R.font.montserrat_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),

    // Regular
    Font(resId = R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(resId = R.font.montserrat_italic, weight = FontWeight.Normal, style = FontStyle.Italic),

    // Medium
    Font(resId = R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(resId = R.font.montserrat_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),

    // SemiBold

    Font(resId = R.font.montserrat_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.montserrat_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),

    // Bold
    Font(resId = R.font.montserrat_bold, weight = FontWeight.Bold),
    Font(resId = R.font.montserrat_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),

    // ExtraBold
    Font(resId = R.font.montserrat_extra_bold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.montserrat_extra_bold_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),

    // Black
    Font(resId = R.font.montserrat_black, weight = FontWeight.Black),
    Font(resId = R.font.montserrat_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
)


val RecipeTypography = Typography(
    // Display — largest, most prominent (e.g. splash/hero text)
    displayLarge = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),
    displaySmall = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),

    // Headline — section headers, screen titles
    headlineLarge = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ),

    // Title — card headers, list item titles
    titleLarge = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 20.sp
    ),
    titleSmall = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 18.sp
    ),

    // Body — main readable content
    bodyLarge = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp
    ),
    bodySmall = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 11.sp,
        lineHeight = 16.sp
    ),

    // Label — buttons, tags, captions
    labelLarge = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = RecipeAppFontFamily,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 11.sp,
        lineHeight = 14.sp
    )
)