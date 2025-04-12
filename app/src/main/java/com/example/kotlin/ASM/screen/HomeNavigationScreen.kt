package com.example.kotlin.ASM.screen


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotlin.ASM.data.CartDataStore

@Composable
fun HomeNavigationScreen() {
    val navController = rememberNavController()

    // Get current route to determine which tab should be selected in bottom nav
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Determine which bottom nav item should be selected based on the current route
    val currentBottomNavRoute = when {
        currentRoute == BottomNavItem.Home.route -> BottomNavItem.Home.route
        currentRoute?.startsWith("productDetail") == true -> BottomNavItem.Home.route
        currentRoute == BottomNavItem.Product.route ||
                currentRoute?.startsWith("${BottomNavItem.Product.route}?") == true ||
                currentRoute == "checkout" ||
                currentRoute == "congrats" -> BottomNavItem.Product.route

        currentRoute == BottomNavItem.Notifications.route -> BottomNavItem.Notifications.route
        currentRoute == BottomNavItem.Profile.route -> BottomNavItem.Profile.route
        else -> BottomNavItem.Home.route
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                currentRoute = currentBottomNavRoute
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            // Home and main navigation
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    onProductClick = { name, price, imageRes ->
                        navController.navigate("productDetail/$name/$price/$imageRes")
                    },
                    onFavoriteClick = { name, price, imageRes ->
                        // Add to cart directly
                        val cartItem = CartItem(
                            id = System.currentTimeMillis().toInt(),
                            name = name,
                            price = price.toDouble(),
                            imageRes = imageRes,
                            quantity = 1
                        )
                        CartDataStore.addProduct(cartItem)

                        // Navigate to cart screen
                        navController.navigate(BottomNavItem.Product.route) {
                            popUpTo(BottomNavItem.Home.route)
                        }
                    }
                )
            }

            // Product detail screen
            composable(
                route = "productDetail/{name}/{price}/{imageRes}",
                arguments = listOf(
                    navArgument("name") { type = NavType.StringType },
                    navArgument("price") { type = NavType.FloatType },
                    navArgument("imageRes") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: "Unknown"
                val price = backStackEntry.arguments?.getFloat("price") ?: 0f
                val imageRes = backStackEntry.arguments?.getInt("imageRes") ?: 0

                ProductDetailScreenUI(
                    name = name,
                    price = price.toDouble(),
                    imageRes = imageRes,
                    onBackClick = { navController.popBackStack() },
                    onAddToCartClick = { quantity ->
                        // Add to cart and navigate back to home
                        val cartItem = CartItem(
                            id = System.currentTimeMillis().toInt(),
                            name = name,
                            price = price.toDouble(),
                            imageRes = imageRes,
                            quantity = quantity
                        )
                        CartDataStore.addProduct(cartItem)

                        // Navigate to cart screen
                        navController.navigate(BottomNavItem.Product.route) {
                            popUpTo(BottomNavItem.Home.route)
                        }
                    }
                )
            }

            // Cart screen
            composable(BottomNavItem.Product.route) {
                CartScreen(
                    onCheckoutClick = { totalAmount ->
                        navController.navigate("checkout/$totalAmount")
                    },
                    onBackClick = {
                        // Navigate back to home screen
                        navController.navigate(BottomNavItem.Home.route) {
                            // Clear the back stack
                            popUpTo(0) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            // Checkout screen
            composable(
                route = "checkout/{totalAmount}",
                arguments = listOf(
                    navArgument("totalAmount") { type = NavType.FloatType }
                )
            ) { backStackEntry ->
                val totalAmount = backStackEntry.arguments?.getFloat("totalAmount") ?: 0f

                CheckoutScreen(
                    totalAmount = totalAmount.toDouble(),
                    onBackClick = { navController.popBackStack() },
                    onCompleteCheckout = {
                        // Clear the cart after successful checkout
                        CartDataStore.clearCart()
                        navController.navigate("congrats")
                    }
                )
            }

            // Congrats screen after successful checkout
            composable("congrats") {
                CongratsScreen(
                    onContinueShopping = {
                        // Navigate back to home and clear the back stack
                        navController.navigate(BottomNavItem.Home.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            // Other bottom navigation tabs
            composable(BottomNavItem.Notifications.route) {
                NotificationScreen()
            }

            composable(BottomNavItem.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeNavigationScreenPreview() {
    HomeNavigationScreen()
}