package com.example.kotlin.Lab4.p2

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun S2(phoneNumber: String, onNext: () -> Unit) {
    var countdown by remember { mutableIntStateOf(59) }
    val keyboardController = LocalSoftwareKeyboardController.current
    var inputValues by remember { mutableStateOf(List(5) { "" }) }

    LaunchedEffect(countdown) {
        if (countdown > 0) {
            delay(1000L)
            countdown--
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
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
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Enter code from SMS", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "We have sent a message to phone $phoneNumber",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            inputValues.forEachIndexed { index, value ->
                BasicTextField(
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                            val updatedValues = inputValues.toMutableList()
                            updatedValues[index] = newValue
                            inputValues = updatedValues
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier
                        .size(48.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .clickable {
                            keyboardController?.show()
                        }
                        .padding(8.dp),
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                countdown = 59
                keyboardController?.show()
                onNext()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF45BC1B))
        ) {
            Text(text = "Request code via $countdown", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "By clicking on the \"Confirm Login\" button, I agree to the terms of use of the service",
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun S2Preview() {
    S2("0774749399") {}
}