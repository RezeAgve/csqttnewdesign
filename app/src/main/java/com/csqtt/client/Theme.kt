package com.csqtt.client

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.csqtt.client.ui.design.CsqttDarkExtendedColors
import com.csqtt.client.ui.design.CsqttLightExtendedColors
import com.csqtt.client.ui.design.CsqttMaterialShapes
import com.csqtt.client.ui.design.LocalCsqttExtendedColors

val CSQTTTypography = Typography(
    displayLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 57.sp, letterSpacing = (-0.5).sp),
    displayMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 45.sp, letterSpacing = (-0.4).sp),
    displaySmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 36.sp, letterSpacing = (-0.3).sp),
    headlineLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = 32.sp, letterSpacing = (-0.3).sp),
    headlineMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = 28.sp, letterSpacing = (-0.2).sp),
    headlineSmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = 24.sp, letterSpacing = (-0.2).sp),
    titleLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, letterSpacing = (-0.15).sp),
    titleMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, letterSpacing = (-0.1).sp),
    titleSmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 14.sp),
    bodyLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodySmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    labelLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
    labelMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 12.sp),
    labelSmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 11.sp),
)

// Apple iOS Dark Mode Palette
private val AppleDarkColorScheme = darkColorScheme(
    primary = Color(0xFF0A84FF),              // iOS System Blue
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF003A75),
    onPrimaryContainer = Color(0xFFD6E9FF),
    secondary = Color(0xFF5E5CE6),            // iOS System Indigo
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFF252459),
    onSecondaryContainer = Color(0xFFE0DFFF),
    tertiary = Color(0xFF30D158),             // iOS System Green
    onTertiary = Color(0xFF003912),
    tertiaryContainer = Color(0xFF0A3D1B),
    onTertiaryContainer = Color(0xFFBDF7CA),
    background = Color(0xFF000000),           // Pure OLED Black
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF1C1C1E),              // iOS Secondary System Grouped Background
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF2C2C2E),       // iOS Tertiary System Fill
    onSurfaceVariant = Color(0xFF8E8E93),     // iOS Secondary Label
    outline = Color(0xFF38383A),              // iOS Separator
    outlineVariant = Color(0xFF2C2C2E),
    error = Color(0xFFFF453A),                // iOS System Red
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFF4A1210),
    onErrorContainer = Color(0xFFFFDAD6),
    inverseSurface = Color(0xFFF2F2F7),
    inverseOnSurface = Color(0xFF1C1C1E),
    inversePrimary = Color(0xFF007AFF),
    surfaceTint = Color.Transparent,
)

// Apple iOS Light Mode Palette
private val AppleLightColorScheme = lightColorScheme(
    primary = Color(0xFF007AFF),              // iOS System Blue Light
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD6E9FF),
    onPrimaryContainer = Color(0xFF002B59),
    secondary = Color(0xFF5856D6),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE0DFFF),
    onSecondaryContainer = Color(0xFF1E1C59),
    tertiary = Color(0xFF34C759),             // iOS System Green Light
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFD1F7DC),
    onTertiaryContainer = Color(0xFF063B14),
    background = Color(0xFFF2F2F7),           // iOS System Grouped Background
    onBackground = Color(0xFF000000),
    surface = Color(0xFFFFFFFF),              // Pure White Cards
    onSurface = Color(0xFF000000),
    surfaceVariant = Color(0xFFE5E5EA),
    onSurfaceVariant = Color(0xFF6C6C70),
    outline = Color(0xFFC6C6C8),
    outlineVariant = Color(0xFFE5E5EA),
    error = Color(0xFFFF3B30),
    onError = Color(0xFFFFFFFF),
    surfaceTint = Color.Transparent,
)

private fun getAppColorScheme(isDark: Boolean): androidx.compose.material3.ColorScheme =
    if (isDark) AppleDarkColorScheme else AppleLightColorScheme

object CSQTTColors {
    val connected = Color(0xFF30D158)         // iOS Green
    val brand = Color(0xFF0A84FF)             // iOS Blue
    val brandDeep = Color(0xFF007AFF)

    val warning = Color(0xFFFF9F0A)           // iOS Orange

    val terminalBg = Color(0xFF1C1C1E)
    val terminalBgDark = Color(0xFF000000)
    val terminalText = Color(0xFFF2F2F7)
    val terminalGreen = Color(0xFF30D158)
    val terminalBlue = Color(0xFF0A84FF)
    val terminalCyan = Color(0xFF64D2FF)
    val terminalRed = Color(0xFFFF453A)
    val terminalCounter = Color(0xFF2C2C2E)

    val donate = Color(0xFF0A84FF)
    val telegram = Color(0xFF0A84FF)
}

@Composable
fun CSQTTTheme(
    themeMode: String = CsqttConstants.Theme.MODE_DARK,
    content: @Composable () -> Unit
) {
    val darkTheme = when (themeMode) {
        CsqttConstants.Theme.MODE_DARK -> true
        CsqttConstants.Theme.MODE_LIGHT -> false
        else -> isSystemInDarkTheme()
    }

    val colorScheme = remember(darkTheme) {
        getAppColorScheme(darkTheme)
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        @Suppress("DEPRECATION")
        SideEffect {
            val window = (view.context as Activity).window
            val navigationBarColor = if (darkTheme) {
                Color.Transparent
            } else {
                lerp(colorScheme.background, colorScheme.surface, 0.55f)
            }
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = navigationBarColor.toArgb()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false
                window.isStatusBarContrastEnforced = false
            }
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    val currentDensity = LocalDensity.current
    val fixedDensity = remember(currentDensity.density) {
        Density(density = currentDensity.density, fontScale = 1f)
    }

    CompositionLocalProvider(
        LocalCsqttExtendedColors provides if (darkTheme) CsqttDarkExtendedColors else CsqttLightExtendedColors,
        LocalDensity provides fixedDensity,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = CSQTTTypography,
            shapes = CsqttMaterialShapes,
            content = content,
        )
    }
}
