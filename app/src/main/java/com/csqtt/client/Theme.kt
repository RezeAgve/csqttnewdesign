package com.csqtt.client

import android.os.Build
import android.app.Activity
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
import com.csqtt.client.CsqttConstants
import com.csqtt.client.ui.design.CsqttDarkExtendedColors
import com.csqtt.client.ui.design.CsqttLightExtendedColors
import com.csqtt.client.ui.design.CsqttMaterialShapes
import com.csqtt.client.ui.design.LocalCsqttExtendedColors

val CSQTTTypography = Typography(
    displayLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 57.sp),
    displayMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 45.sp),
    displaySmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold, fontSize = 36.sp),
    headlineLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = 32.sp),
    headlineMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = 28.sp),
    headlineSmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = 24.sp),
    titleLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.SemiBold, fontSize = 22.sp),
    titleMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 16.sp),
    titleSmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 14.sp),
    bodyLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodySmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    labelLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 14.sp),
    labelMedium = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 12.sp),
    labelSmall = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Medium, fontSize = 11.sp),
)

private val AmneziaDarkColorScheme = darkColorScheme(
    primary = Color(0xFFF97316),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF3A1F0D),
    onPrimaryContainer = Color(0xFFFFDBCA),
    secondary = Color(0xFFFB923C),
    onSecondary = Color(0xFF1A110A),
    secondaryContainer = Color(0xFF2B1C12),
    onSecondaryContainer = Color(0xFFFFDBCA),
    tertiary = Color(0xFF38BDF8),
    onTertiary = Color(0xFF082032),
    tertiaryContainer = Color(0xFF102A3E),
    onTertiaryContainer = Color(0xFFBAE6FD),
    background = Color(0xFF0E0F12),
    onBackground = Color(0xFFF3F4F6),
    surface = Color(0xFF181A20),
    onSurface = Color(0xFFF3F4F6),
    surfaceVariant = Color(0xFF22252E),
    onSurfaceVariant = Color(0xFF9CA3AF),
    outline = Color(0xFF323642),
    outlineVariant = Color(0xFF262932),
    error = Color(0xFFEF4444),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFF450A0A),
    onErrorContainer = Color(0xFFFECACA),
    inverseSurface = Color(0xFFF3F4F6),
    inverseOnSurface = Color(0xFF181A20),
    inversePrimary = Color(0xFFEA580C),
    surfaceTint = Color.Transparent,
)

private val AmneziaLightColorScheme = lightColorScheme(
    primary = Color(0xFFEA580C),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFEDD5),
    onPrimaryContainer = Color(0xFF431407),
    secondary = Color(0xFFF97316),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFEDD5),
    onSecondaryContainer = Color(0xFF431407),
    tertiary = Color(0xFF0284C7),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFE0F2FE),
    onTertiaryContainer = Color(0xFF0C4A6E),
    background = Color(0xFFF8FAFC),
    onBackground = Color(0xFF0F172A),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = Color(0xFF475569),
    outline = Color(0xFFCBD5E1),
    outlineVariant = Color(0xFFE2E8F0),
    surfaceTint = Color.Transparent,
)

private fun getAppColorScheme(isDark: Boolean): androidx.compose.material3.ColorScheme =
    if (isDark) AmneziaDarkColorScheme else AmneziaLightColorScheme

object CSQTTColors {
    val connected = Color(0xFF22C55E)
    val brand = Color(0xFFF97316)
    val brandDeep = Color(0xFFEA580C)

    val warning = Color(0xFFF59E0B)

    val terminalBg = Color(0xFF12141A)
    val terminalBgDark = Color(0xFF0A0B0E)
    val terminalText = Color(0xFFE5E7EB)
    val terminalGreen = Color(0xFF22C55E)
    val terminalBlue = Color(0xFF38BDF8)
    val terminalCyan = Color(0xFF2DD4BF)
    val terminalRed = Color(0xFFEF4444)
    val terminalCounter = Color(0xFF1E222B)

    val donate = Color(0xFFF97316)
    val telegram = Color(0xFF38BDF8)
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
