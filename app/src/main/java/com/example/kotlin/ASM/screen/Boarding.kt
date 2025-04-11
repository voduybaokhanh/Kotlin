package com.example.kotlin.ASM.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kotlin.R

@Composable
fun BoardingScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.boarding_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(30.dp)
        ) {
            Text(
                text = "MAKE YOUR",
                color = Color(0xFF606060),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 0.05.sp,
                modifier = Modifier
                    .padding(top = 231.dp)
            )
            Text(
                text = "HOME BEAUTIFUL",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 15.dp)
            )

            Text(
                text = "The best simple place where you discover most wonderful furnitures and make your home beautiful",
                color = Color(0xFF808080),
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Justify,
                lineHeight = 26.sp,
                letterSpacing = 0.sp,
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .fillMaxWidth(0.85f)
            )



            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    shape = RectangleShape,
                    onClick = { navController.navigate("login") },
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    modifier = Modifier
                        .width(159.dp)
                        .height(54.dp)
                        .clip(RoundedCornerShape(4.dp))
                ) {
                    Text("Get Started", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BoardingScreenPreview() {
    BoardingScreen(navController = rememberNavController())
}