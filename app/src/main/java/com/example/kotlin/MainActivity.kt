package com.example.kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin.Lab4.p2.S1
import com.example.kotlin.Lab4.p2.S2
import com.example.kotlin.Lab4.p2.S3
import com.example.kotlin.Lab4.p2.S4
import com.example.kotlin.ui.theme.KotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTheme {
                // A surface container using the 'background' color from the theme
                MaterialTheme {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var phoneNumber by remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "S1") {
        composable("S1") { S1(phoneNumber) { phoneNumber = it; navController.navigate("S2") } }
        composable("S2") { S2(phoneNumber) { navController.navigate("S3") } }
        composable("S3") { S3 { navController.navigate("S4") } }
        composable("S4") { S4() }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}