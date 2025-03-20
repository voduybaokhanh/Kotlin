package com.example.kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin.Lab3.CalculatorScreen
import com.example.kotlin.Lab3.ClickApp
import com.example.kotlin.Lab3.DangKy
import com.example.kotlin.Lab3.PoemScreen
import com.example.kotlin.Lab3.PreviewLab3
import com.example.kotlin.ui.theme.KotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ -> // Use '_' instead of 'innerPadding'
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "summary") {
        composable("summary") { PreviewLab3(navController) }
        composable("preview1") { DangKy() }
        composable("preview2") { ClickApp() }
        composable("preview3") { PoemScreen() }
        composable("preview4") { CalculatorScreen() }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MaterialTheme {
        MyApp()
    }
}