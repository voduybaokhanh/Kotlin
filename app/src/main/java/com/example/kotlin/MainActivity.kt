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
import com.example.kotlin.Lab4.ProductScreen
import com.example.kotlin.Lab4.RegisterScreen
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
        composable("summary") { RegisterScreen(navController) }
        composable("cuahang") { ProductScreen(navController) }
//        composable("preview1") { DangKy() }
//        composable("preview2") { ClickApp() }
//        composable("preview3") { PoemScreen() }
//        composable("preview4") { CalculatorScreen() }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MaterialTheme {
        MyApp()
    }
}