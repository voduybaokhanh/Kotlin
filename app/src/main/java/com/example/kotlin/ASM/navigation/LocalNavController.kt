package com.example.kotlin.ASM.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

// Create a CompositionLocal for NavController
val LocalNavController = compositionLocalOf<NavController> { 
    error("No NavController provided") 
}