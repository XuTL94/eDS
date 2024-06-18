package ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

// 定义浅色主题的颜色方案
private val lightScheme = lightColorScheme(
    primary = LightColors.primary,
    onPrimary = LightColors.onPrimary,
    primaryContainer = LightColors.primaryContainer,
    onPrimaryContainer = LightColors.onPrimaryContainer,
    secondary = LightColors.secondary,
    onSecondary = LightColors.onSecondary,
    secondaryContainer = LightColors.secondaryContainer,
    onSecondaryContainer = LightColors.onSecondaryContainer,
    tertiary = LightColors.tertiary,
    onTertiary = LightColors.onTertiary,
    tertiaryContainer = LightColors.tertiaryContainer,
    onTertiaryContainer = LightColors.onTertiaryContainer,
    error = LightColors.error,
    onError = LightColors.onError,
    errorContainer = LightColors.errorContainer,
    onErrorContainer = LightColors.onErrorContainer,
    background = LightColors.background,
    onBackground = LightColors.onBackground,
    surface = LightColors.surface,
    onSurface = LightColors.onSurface,
    surfaceVariant = LightColors.surfaceVariant,
    onSurfaceVariant = LightColors.onSurfaceVariant,
    outline = LightColors.outline,
    outlineVariant = LightColors.outlineVariant,
    scrim = LightColors.scrim,
    inverseSurface = LightColors.inverseSurface,
    inverseOnSurface = LightColors.inverseOnSurface,
    inversePrimary = LightColors.inversePrimary,
    surfaceDim = LightColors.surfaceDim,
    surfaceBright = LightColors.surfaceBright,
    surfaceContainerLowest = LightColors.surfaceContainerLowest,
    surfaceContainerLow = LightColors.surfaceContainerLow,
    surfaceContainer = LightColors.surfaceContainer,
    surfaceContainerHigh = LightColors.surfaceContainerHigh,
    surfaceContainerHighest = LightColors.surfaceContainerHighest
)

// 定义深色主题的颜色方案
private val darkScheme = darkColorScheme(
    primary = DarkColors.primary,
    onPrimary = DarkColors.onPrimary,
    primaryContainer = DarkColors.primaryContainer,
    onPrimaryContainer = DarkColors.onPrimaryContainer,
    secondary = DarkColors.secondary,
    onSecondary = DarkColors.onSecondary,
    secondaryContainer = DarkColors.secondaryContainer,
    onSecondaryContainer = DarkColors.onSecondaryContainer,
    tertiary = DarkColors.tertiary,
    onTertiary = DarkColors.onTertiary,
    tertiaryContainer = DarkColors.tertiaryContainer,
    onTertiaryContainer = DarkColors.onTertiaryContainer,
    error = DarkColors.error,
    onError = DarkColors.onError,
    errorContainer = DarkColors.errorContainer,
    onErrorContainer = DarkColors.onErrorContainer,
    background = DarkColors.background,
    onBackground = DarkColors.onBackground,
    surface = DarkColors.surface,
    onSurface = DarkColors.onSurface,
    surfaceVariant = DarkColors.surfaceVariant,
    onSurfaceVariant = DarkColors.onSurfaceVariant,
    outline = DarkColors.outline,
    outlineVariant = DarkColors.outlineVariant,
    scrim = DarkColors.scrim,
    inverseSurface = DarkColors.inverseSurface,
    inverseOnSurface = DarkColors.inverseOnSurface,
    inversePrimary = DarkColors.inversePrimary,
    surfaceDim = DarkColors.surfaceDim,
    surfaceBright = DarkColors.surfaceBright,
    surfaceContainerLowest = DarkColors.surfaceContainerLowest,
    surfaceContainerLow = DarkColors.surfaceContainerLow,
    surfaceContainer = DarkColors.surfaceContainer,
    surfaceContainerHigh = DarkColors.surfaceContainerHigh,
    surfaceContainerHighest = DarkColors.surfaceContainerHighest
)

// 中等对比度浅色主题的颜色方案
private val lightMediumContrastScheme = lightColorScheme(
    primary = LightMediumContrastColors.primary,
    onPrimary = LightMediumContrastColors.onPrimary,
    primaryContainer = LightMediumContrastColors.primaryContainer,
    onPrimaryContainer = LightMediumContrastColors.onPrimaryContainer,
    secondary = LightMediumContrastColors.secondary,
    onSecondary = LightMediumContrastColors.onSecondary,
    secondaryContainer = LightMediumContrastColors.secondaryContainer,
    onSecondaryContainer = LightMediumContrastColors.onSecondaryContainer,
    tertiary = LightMediumContrastColors.tertiary,
    onTertiary = LightMediumContrastColors.onTertiary,
    tertiaryContainer = LightMediumContrastColors.tertiaryContainer,
    onTertiaryContainer = LightMediumContrastColors.onTertiaryContainer,
    error = LightMediumContrastColors.error,
    onError = LightMediumContrastColors.onError,
    errorContainer = LightMediumContrastColors.errorContainer,
    onErrorContainer = LightMediumContrastColors.onErrorContainer,
    background = LightMediumContrastColors.background,
    onBackground = LightMediumContrastColors.onBackground,
    surface = LightMediumContrastColors.surface,
    onSurface = LightMediumContrastColors.onSurface,
    surfaceVariant = LightMediumContrastColors.surfaceVariant,
    onSurfaceVariant = LightMediumContrastColors.onSurfaceVariant,
    outline = LightMediumContrastColors.outline,
    outlineVariant = LightMediumContrastColors.outlineVariant,
    scrim = LightMediumContrastColors.scrim,
    inverseSurface = LightMediumContrastColors.inverseSurface,
    inverseOnSurface = LightMediumContrastColors.inverseOnSurface,
    inversePrimary = LightMediumContrastColors.inversePrimary,
    surfaceDim = LightMediumContrastColors.surfaceDim,
    surfaceBright = LightMediumContrastColors.surfaceBright,
    surfaceContainerLowest = LightMediumContrastColors.surfaceContainerLowest,
    surfaceContainerLow = LightMediumContrastColors.surfaceContainerLow,
    surfaceContainer = LightMediumContrastColors.surfaceContainer,
    surfaceContainerHigh = LightMediumContrastColors.surfaceContainerHigh,
    surfaceContainerHighest = LightMediumContrastColors.surfaceContainerHighest
)

// 高对比度浅色主题的颜色方案
private val lightHighContrastScheme = lightColorScheme(
    primary = LightHighContrastColors.primary,
    onPrimary = LightHighContrastColors.onPrimary,
    primaryContainer = LightHighContrastColors.primaryContainer,
    onPrimaryContainer = LightHighContrastColors.onPrimaryContainer,
    secondary = LightHighContrastColors.secondary,
    onSecondary = LightHighContrastColors.onSecondary,
    secondaryContainer = LightHighContrastColors.secondaryContainer,
    onSecondaryContainer = LightHighContrastColors.onSecondaryContainer,
    tertiary = LightHighContrastColors.tertiary,
    onTertiary = LightHighContrastColors.onTertiary,
    tertiaryContainer = LightHighContrastColors.tertiaryContainer,
    onTertiaryContainer = LightHighContrastColors.onTertiaryContainer,
    error = LightHighContrastColors.error,
    onError = LightHighContrastColors.onError,
    errorContainer = LightHighContrastColors.errorContainer,
    onErrorContainer = LightHighContrastColors.onErrorContainer,
    background = LightHighContrastColors.background,
    onBackground = LightHighContrastColors.onBackground,
    surface = LightHighContrastColors.surface,
    onSurface = LightHighContrastColors.onSurface,
    surfaceVariant = LightHighContrastColors.surfaceVariant,
    onSurfaceVariant = LightHighContrastColors.onSurfaceVariant,
    outline = LightHighContrastColors.outline,
    outlineVariant = LightHighContrastColors.outlineVariant,
    scrim = LightHighContrastColors.scrim,
    inverseSurface = LightHighContrastColors.inverseSurface,
    inverseOnSurface = LightHighContrastColors.inverseOnSurface,
    inversePrimary = LightHighContrastColors.inversePrimary,
    surfaceDim = LightHighContrastColors.surfaceDim,
    surfaceBright = LightHighContrastColors.surfaceBright,
    surfaceContainerLowest = LightHighContrastColors.surfaceContainerLowest,
    surfaceContainerLow = LightHighContrastColors.surfaceContainerLow,
    surfaceContainer = LightHighContrastColors.surfaceContainer,
    surfaceContainerHigh = LightHighContrastColors.surfaceContainerHigh,
    surfaceContainerHighest = LightHighContrastColors.surfaceContainerHighest
)

// 中等对比度深色主题的颜色方案
private val darkMediumContrastScheme = darkColorScheme(
    primary = DarkMediumContrastColors.primary,
    onPrimary = DarkMediumContrastColors.onPrimary,
    primaryContainer = DarkMediumContrastColors.primaryContainer,
    onPrimaryContainer = DarkMediumContrastColors.onPrimaryContainer,
    secondary = DarkMediumContrastColors.secondary,
    onSecondary = DarkMediumContrastColors.onSecondary,
    secondaryContainer = DarkMediumContrastColors.secondaryContainer,
    onSecondaryContainer = DarkMediumContrastColors.onSecondaryContainer,
    tertiary = DarkMediumContrastColors.tertiary,
    onTertiary = DarkMediumContrastColors.onTertiary,
    tertiaryContainer = DarkMediumContrastColors.tertiaryContainer,
    onTertiaryContainer = DarkMediumContrastColors.onTertiaryContainer,
    error = DarkMediumContrastColors.error,
    onError = DarkMediumContrastColors.onError,
    errorContainer = DarkMediumContrastColors.errorContainer,
    onErrorContainer = DarkMediumContrastColors.onErrorContainer,
    background = DarkMediumContrastColors.background,
    onBackground = DarkMediumContrastColors.onBackground,
    surface = DarkMediumContrastColors.surface,
    onSurface = DarkMediumContrastColors.onSurface,
    surfaceVariant = DarkMediumContrastColors.surfaceVariant,
    onSurfaceVariant = DarkMediumContrastColors.onSurfaceVariant,
    outline = DarkMediumContrastColors.outline,
    outlineVariant = DarkMediumContrastColors.outlineVariant,
    scrim = DarkMediumContrastColors.scrim,
    inverseSurface = DarkMediumContrastColors.inverseSurface,
    inverseOnSurface = DarkMediumContrastColors.inverseOnSurface,
    inversePrimary = DarkMediumContrastColors.inversePrimary,
    surfaceDim = DarkMediumContrastColors.surfaceDim,
    surfaceBright = DarkMediumContrastColors.surfaceBright,
    surfaceContainerLowest = DarkMediumContrastColors.surfaceContainerLowest,
    surfaceContainerLow = DarkMediumContrastColors.surfaceContainerLow,
    surfaceContainer = DarkMediumContrastColors.surfaceContainer,
    surfaceContainerHigh = DarkMediumContrastColors.surfaceContainerHigh,
    surfaceContainerHighest = DarkMediumContrastColors.surfaceContainerHighest
)

// 高对比度深色主题的颜色方案
private val darkHighContrastScheme = darkColorScheme(
    primary = DarkHighContrastColors.primary,
    onPrimary = DarkHighContrastColors.onPrimary,
    primaryContainer = DarkHighContrastColors.primaryContainer,
    onPrimaryContainer = DarkHighContrastColors.onPrimaryContainer,
    secondary = DarkHighContrastColors.secondary,
    onSecondary = DarkHighContrastColors.onSecondary,
    secondaryContainer = DarkHighContrastColors.secondaryContainer,
    onSecondaryContainer = DarkHighContrastColors.onSecondaryContainer,
    tertiary = DarkHighContrastColors.tertiary,
    onTertiary = DarkHighContrastColors.onTertiary,
    tertiaryContainer = DarkHighContrastColors.tertiaryContainer,
    onTertiaryContainer = DarkHighContrastColors.onTertiaryContainer,
    error = DarkHighContrastColors.error,
    onError = DarkHighContrastColors.onError,
    errorContainer = DarkHighContrastColors.errorContainer,
    onErrorContainer = DarkHighContrastColors.onErrorContainer,
    background = DarkHighContrastColors.background,
    onBackground = DarkHighContrastColors.onBackground,
    surface = DarkHighContrastColors.surface,
    onSurface = DarkHighContrastColors.onSurface,
    surfaceVariant = DarkHighContrastColors.surfaceVariant,
    onSurfaceVariant = DarkHighContrastColors.onSurfaceVariant,
    outline = DarkHighContrastColors.outline,
    outlineVariant = DarkHighContrastColors.outlineVariant,
    scrim = DarkHighContrastColors.scrim,
    inverseSurface = DarkHighContrastColors.inverseSurface,
    inverseOnSurface = DarkHighContrastColors.inverseOnSurface,
    inversePrimary = DarkHighContrastColors.inversePrimary,
    surfaceDim = DarkHighContrastColors.surfaceDim,
    surfaceBright = DarkHighContrastColors.surfaceBright,
    surfaceContainerLowest = DarkHighContrastColors.surfaceContainerLowest,
    surfaceContainerLow = DarkHighContrastColors.surfaceContainerLow,
    surfaceContainer = DarkHighContrastColors.surfaceContainer,
    surfaceContainerHigh = DarkHighContrastColors.surfaceContainerHigh,
    surfaceContainerHighest = DarkHighContrastColors.surfaceContainerHighest
)


// 应用主题的Composable函数
@Composable
fun AppTheme(
    content: @Composable () -> Unit // 主题内容
) {
    // 设置Material主题
    MaterialTheme(
        colorScheme = lightScheme,
        typography = AppTypography, // 字体样式
        content = content // 内容
    )
}