package com.example.kotlin.ASM

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Hello !",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFF303030)
                    )
                    Text(
                        text = "WELCOME BACK",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W700,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier.fillMaxWidth(0.8f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = androidx.compose.material3.TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Password") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = androidx.compose.material3.TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "Forgot Password",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        onClick = {},
                        shape = RectangleShape,
                        modifier = Modifier
                            .width(285.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        colors = ButtonDefaults.buttonColors(Color.Black)
                    ) {
                        Text("Log in", color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = "SIGN UP",
                        color = Color.Black,
                        fontSize = 18.sp,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLogin() {
    LoginScreen()
}
