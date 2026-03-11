package com.example.audioxel.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.audioxel.navigation.BottomNavItem
import com.example.audioxel.ui.theme.Background
import com.example.audioxel.ui.theme.OnSurfaceVariant
import com.example.audioxel.ui.theme.Primary
import com.example.audioxel.ui.theme.Surface

@Composable
fun BottomNavBar(
    currentRoute: String,
    onItemClick: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Explore,
        BottomNavItem.Library,
        BottomNavItem.Profile,
    )

    NavigationBar(
        modifier = modifier,
        containerColor = Surface,
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = stringResource(item.labelRes),
                    )
                },
                label = {
                    Text(text = stringResource(item.labelRes))
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Primary,
                    selectedTextColor = Primary,
                    unselectedIconColor = OnSurfaceVariant,
                    unselectedTextColor = OnSurfaceVariant,
                    indicatorColor = Background,
                ),
            )
        }
    }
}
