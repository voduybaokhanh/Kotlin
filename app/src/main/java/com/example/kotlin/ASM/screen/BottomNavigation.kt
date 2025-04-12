package com.example.kotlin.ASM.screen

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kotlin.R

sealed class BottomNavItem(
    val route: String,
    val icon: Int,
    val selectedIcon: Int,
) {
    object Home : BottomNavItem("home", R.drawable.ic_home_trang, R.drawable.ic_home_den)
    object Product : BottomNavItem(
        "product",
        R.drawable.ic_product_trang,
        R.drawable.ic_product_den
    )

    object Notifications :
        BottomNavItem("notifications", R.drawable.ic_noti_trang, R.drawable.ic_noti_den)

    object Profile :
        BottomNavItem("profile", R.drawable.ic_person_trang, R.drawable.ic_person_den)
}

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String? = null) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Product,
        BottomNavItem.Notifications,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = if (selected) item.selectedIcon else item.icon),
                        contentDescription = item.route,
                        modifier = Modifier.size(24.dp)
                    )
                },
                selected = selected,
                onClick = {
                    // Only navigate if we're not already on this route
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // Clear the entire back stack when navigating between tabs
                            popUpTo(0) {
                                inclusive = true
                            }
                            // Avoid multiple copies of the same destination
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(navController = rememberNavController())
}