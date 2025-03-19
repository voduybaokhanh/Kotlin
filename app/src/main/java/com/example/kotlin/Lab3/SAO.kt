package com.example.kotlin.Lab3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun PoemScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SAO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        val redText = listOf(
            "Một ngôi sao vừa rơi",
            "Vụt tắt trên bầu trời",
            "Hay là tên người ấy",
            "Vụt tắt ở trong tôi?"
        )
        val blueText = listOf(
            "Vẫn thấy trên bầu trời",
            "Có muôn vàn sao sáng",
            "Mà ở trong lòng tôi",
            "Như một hành lang vắng"
        )
        val greenText = listOf(
            "Một ngôi sao vừa tắt",
            "Bầu trời vẫn không buồn",
            "Sao tên người ấy tắt",
            "Trong lòng tôi cô đơn?"
        )

        DisplayPoem(redText, Color.Red)
        DisplayPoem(blueText, Color.Blue)
        DisplayPoem(greenText, Color.Green)
    }
}

@Composable
fun DisplayPoem(lines: List<String>, color: Color) {
    lines.forEach { line ->
        Text(
            text = buildAnnotatedString {
                val words = line.split(" ")
                words.forEachIndexed { index, word ->
                    if (word.equals("sao", ignoreCase = true)) {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(word)
                        }
                    } else {
                        append(word)
                    }
                    if (index < words.size - 1) append(" ")
                }
            },
            fontSize = 20.sp,
            color = color
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}
