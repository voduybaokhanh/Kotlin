package com.example.kotlin.ASM

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        TopAppBar(
            title = {
                Text(
                    "Notification",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(20.dp)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            )
        )

        val notifications = List(10) { index ->
            NotificationData(
                orderId = "#123456789",
                status = "has been confirmed",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu adipiscing nec.",
                isNew = index == 0
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
        ) {
            items(notifications) { notification ->
                NotificationItem(
                    orderId = notification.orderId,
                    status = notification.status,
                    description = notification.description,
                    isNew = notification.isNew
                )
                if (notification.isNew) {
                    HotSaleItem(
                        title = "Discover hot sale furnitures this week.",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis pretium et in arcu turpis nunc."
                    )
                }
            }
        }
    }
}

data class NotificationData(
    val orderId: String,
    val status: String,
    val description: String,
    val isNew: Boolean
)

@Composable
fun NotificationItem(orderId: String, status: String, description: String, isNew: Boolean = false) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_placeholder),
            contentDescription = "Product image",
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Your order $orderId $status",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray
            )
            if (isNew) {
                Text(
                    text = "New",
                    fontSize = 14.sp,
                    color = Color(0x27AE60).copy(alpha = 1.0f),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
fun HotSaleItem(title: String, description: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = "HOT!",
            fontSize = 14.sp,
            color = Color(0xEB5757).copy(alpha = 1.0f),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNotificationScreen() {
    NotificationScreen()
}