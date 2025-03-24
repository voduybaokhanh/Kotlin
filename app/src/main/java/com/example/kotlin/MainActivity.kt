package com.example.kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin.ASM.BoardingScreen
import com.example.kotlin.ASM.LoginScreen
import com.example.kotlin.ASM.NotificationScreen
import com.example.kotlin.ASM.SignUpScreen
import com.example.kotlin.ui.theme.KotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "summary") {
        composable("chao") { BoardingScreen(navController) }
        composable("dangnhap") { LoginScreen(navController) }
        composable("dangky") { SignUpScreen(navController) }
        composable("thongbao") { NotificationScreen(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MaterialTheme {
        MyApp()
    }
}