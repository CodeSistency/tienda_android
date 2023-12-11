package com.plcoding.graphqlcountriesapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.plcoding.graphqlcountriesapp.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName= GoogleFont("MontSerrat")
val fontFamily = FontFamily(
    Font(
        googleFont = fontName,
        fontProvider = provider
    )
)

val fontName2= GoogleFont("Poppins")
val fontFamily2 = FontFamily(
    Font(
        googleFont = fontName2,
        fontProvider = provider
    )
)

val fontName3= GoogleFont("Roboto")
val fontFamily3 = FontFamily(
    Font(
        googleFont = fontName3,
        fontProvider = provider
    )
)
// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ) ,
    button = TextStyle(
        fontFamily = fontFamily2,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = fontFamily3,
        fontSize = 14.sp
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