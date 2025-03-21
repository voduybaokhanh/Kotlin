package com.example.kotlin.Lab4

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin.R

@Preview(showBackground = true)
@Composable
fun ProductScreen() {
    val context = LocalContext.current
    var quantity by remember { mutableIntStateOf(0) }
    var isAvailable by remember { mutableStateOf(true) }
    var message by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Text(text = "Tai nghe Bluetooth", style = MaterialTheme.typography.headlineMedium)
            Text(text = "500.000 VNĐ", style = MaterialTheme.typography.bodyLarge)

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                AssistChip(
                    onClick = {
                        isAvailable = true
                        message = ""
                    },
                    label = { Text("Còn hàng") }
                )
                AssistChip(
                    onClick = {
                        isAvailable = false
                        message = "Sản phẩm đã hết hàng"
                    },
                    label = { Text("Hết hàng") }
                )
            }

            if (isAvailable) {
                OutlinedTextField(
                    value = if (quantity == 0) "" else quantity.toString(),
                    onValueChange = { value ->
                        quantity = value.toIntOrNull() ?: 0
                    },
                    label = { Text("Số lượng") }
                )

                Button(onClick = {
                    message = "Bạn đã chọn $quantity sản phẩm"
                }) {
                    Text("Thêm vào giỏ hàng")
                }
            } else {
                Button(onClick = { message = "Sản phẩm đã hết hàng" }) {
                    Text("Thêm vào giỏ hàng")
                }
            }

            Text(text = message, modifier = Modifier.padding(top = 16.dp))
        }

        FloatingActionButton(
            onClick = {
                Toast.makeText(
                    context,
                    "Đã thêm sản phẩm vào danh sách yêu thích",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Favorite, contentDescription = "Thích")
        }
    }
}
