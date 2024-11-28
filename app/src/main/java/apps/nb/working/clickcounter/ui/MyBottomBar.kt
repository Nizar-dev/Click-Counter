package apps.nb.working.clickcounter.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun MyBottomBar(
    onNavigateToHome: () -> Unit,
    onNavigateToManual: () -> Unit,
    onNavigateToStateful: () -> Unit,
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color.Red, Color.Black)
    )
    var selectedTab by remember { mutableIntStateOf(1) }
    Box {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)

        ) {
            drawRect(gradientBrush)
        }
        NavigationBar(
            modifier = Modifier.fillMaxWidth().height(56.dp),
            tonalElevation = 6.dp,
            containerColor = Color.Transparent,
            contentColor = Color.Transparent
        ) {
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = {
                        onNavigateToHome()
                        selectedTab = 1
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "tab.name",
                            tint = if (selectedTab == 1) {
                                Color.White
                            } else {
                                Color.White.copy(alpha = 0.5f)
                            }
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.5f),
                        indicatorColor = Color.Transparent
                    )
                )
            NavigationBarItem(
                selected = selectedTab == 2,
                onClick = {
                    onNavigateToManual()
                    selectedTab = 2
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = "tab.name",
                        tint = if (selectedTab == 2) {
                            Color.White
                        } else {
                            Color.White.copy(alpha = 0.5f)
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White.copy(alpha = 0.5f),
                    indicatorColor = Color.Transparent
                )
            )
            NavigationBarItem(
                selected = selectedTab == 3,
                onClick = {
                    onNavigateToStateful()
                    selectedTab = 3
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "tab.name",
                        tint = if (selectedTab == 3) {
                            Color.White
                        } else {
                            Color.White.copy(alpha = 0.5f)
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White.copy(alpha = 0.5f),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
