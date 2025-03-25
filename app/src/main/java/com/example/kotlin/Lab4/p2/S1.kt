package com.example.kotlin.Lab4.p2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun S1(phoneNumber: String, onPhoneNumberChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Delivery of products",
            fontSize = 49.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFF45BC1B),
            modifier = Modifier
                .padding(bottom = 50.dp)
                .width(295.dp)
                .wrapContentHeight(),
            lineHeight = 54.sp,
            letterSpacing = (-0.02).sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Authorization or registration",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            placeholder = { Text("Enter phone number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            singleLine = true
        )
        Button(
            onClick = { /* Handle login */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF45BC1B))
        ) {
            Text(text = "Confirm Login", fontSize = 20.sp, color = Color.White)
        }
        Text(
            text = "By clicking on the \"Confirm Login\" button, I agree to the terms of use of the service",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun S1Preview() {
    S1("", {})
}