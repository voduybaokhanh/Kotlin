package com.example.kotlin.ASM

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kotlin.R
import androidx.compose.foundation.layout.size

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
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Product,
        BottomNavItem.Notifications,
        BottomNavItem.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

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
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
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