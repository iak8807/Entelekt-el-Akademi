package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
    primary = DeepNavy,
    onPrimary = WarmIvory,
    primaryContainer = NavyContainer,
    onPrimaryContainer = WarmIvory,
    secondary = MatteGoldDark,
    onSecondary = WarmIvory,
    secondaryContainer = GoldContainerLight,
    onSecondaryContainer = DeepNavy,
    tertiary = ScholarGreen,
    onTertiary = WarmIvory,
    tertiaryContainer = ScholarGreenContainer,
    onTertiaryContainer = Color(0xFF123527),
    background = ParchmentWhite,
    onBackground = InkPrimary,
    surface = WarmIvory,
    onSurface = InkPrimary,
    surfaceVariant = SoftGraySurface,
    onSurfaceVariant = InkSecondary,
    outline = SubtleBorder,
    outlineVariant = Color(0xFFE4E7EC),
    error = ScholarCrimson,
    onError = WarmIvory,
    errorContainer = ScholarCrimsonContainer,
    onErrorContainer = Color(0xFF5D1717)
)

private val DarkColorScheme = darkColorScheme(
    primary = MatteGold,
    onPrimary = DeepNavy,
    primaryContainer = NavyContainer,
    onPrimaryContainer = IvoryText,
    secondary = Color(0xFFD6B566),
    onSecondary = DeepNavy,
    secondaryContainer = Color(0xFF3A311B),
    onSecondaryContainer = GoldContainerLight,
    tertiary = Color(0xFF69B596),
    onTertiary = Color(0xFF0A261B),
    tertiaryContainer = Color(0xFF1B3F31),
    onTertiaryContainer = ScholarGreenContainer,
    background = MidnightBackground,
    onBackground = IvoryText,
    surface = MidnightSurface,
    onSurface = IvoryText,
    surfaceVariant = MidnightSurfaceHigh,
    onSurfaceVariant = MutedSlateText,
    outline = Color(0xFF2D3B55),
    outlineVariant = Color(0xFF212C42),
    error = Color(0xFFE57373),
    onError = Color(0xFF491111),
    errorContainer = Color(0xFF5B1D1D),
    onErrorContainer = ScholarCrimsonContainer
)

val AkademiShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun AkademiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AkademiTypography,
        shapes = AkademiShapes,
        content = content
    )
}
