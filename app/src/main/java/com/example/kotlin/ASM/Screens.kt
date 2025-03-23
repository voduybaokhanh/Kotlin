package com.example.kotlin.ASM

sealed class Screens(val screen: String) {
    object Home : Screens("home")
    object Cart : Screens("cart")
    object Notification : Screens("notification")
    object Profile : Screens("profile")
}
