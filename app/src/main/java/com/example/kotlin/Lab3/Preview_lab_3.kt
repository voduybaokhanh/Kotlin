package com.example.kotlin.Lab3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewLab3(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lab 3 Previews") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { navController.navigate("preview1") }) {
                Text("Preview Bài 1")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("preview2") }) {
                Text("Preview Bài 2")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("preview3") }) {
                Text("Preview Bài 3")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("preview4") }) {
                Text("Preview Bài 4")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLab3Preview() {
    PreviewLab3(rememberNavController())
}