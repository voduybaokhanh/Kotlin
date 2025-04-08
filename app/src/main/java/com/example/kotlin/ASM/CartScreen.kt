package com.example.kotlin.ASM

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin.R
import java.util.Locale


data class CartItem(
    val id: Int,
    val name: String,
    val price: Double,
    val imageRes: Int,
    var quantity: Int = 1
)

@Composable
fun CartScreen() {
    var cartItems by remember {
        mutableStateOf(
            listOf(
                CartItem(1, "Minimal Stand", 25.00, R.drawable.img_stand),
                CartItem(2, "Coffee Table", 20.00, R.drawable.img_table),
                CartItem(3, "Minimal Desk", 50.00, R.drawable.img_desk)
            )
        )
    }
    var promoCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* TODO: Xử lý quay lại */ }, modifier = Modifier.size(24.dp)) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Quay lại",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "My cart",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier.size(24.dp))
        }

        Column(modifier = Modifier.weight(1f)) {
            cartItems.forEachIndexed { index, item ->
                CartItemRow(
                    item = item,
                    onQuantityChanged = { id, newQuantity ->
                        val safeNewQuantity = maxOf(1, newQuantity)
                        cartItems = cartItems.map {
                            if (it.id == id) it.copy(quantity = safeNewQuantity) else it
                        }
                    },
                    onRemoveClicked = { id ->
                        cartItems = cartItems.filter { it.id != id }
                    }
                )
                if (index < cartItems.size - 1) {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = promoCode,
                onValueChange = { promoCode = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp),
                textStyle = LocalTextStyle.current.copy(fontSize = 14.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    Box(contentAlignment = Alignment.CenterStart) {
                        if (promoCode.isEmpty()) {
                            Text("Enter your promo code", fontSize = 14.sp, color = Color.LightGray)
                        }
                        innerTextField()
                    }
                },
                singleLine = true
            )
            IconButton(
                onClick = { /* TODO: Xử lý logic áp dụng mã */ },
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.Black)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "Áp dụng",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        val total = cartItems.sumOf { it.price * it.quantity }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = String.format(Locale.getDefault(), "$ %.2f", total).replace(",", "."),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(
                text = "Check out",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun CartItemRow(
    item: CartItem,
    onQuantityChanged: (Int, Int) -> Unit,
    onRemoveClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Hình ảnh sản phẩm
        Image(
            painter = painterResource(id = item.imageRes.takeIf { it != 0 }
                ?: R.drawable.img_stand),
            contentDescription = item.name,
            modifier = Modifier
                .size(80.dp) // Giữ kích thước ảnh
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.name,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = { onRemoveClicked(item.id) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "Xóa",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Giá sản phẩm
            Text(
                text = String.format(Locale.getDefault(), "$ %.2f", item.price).replace(",", "."),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Nút tăng (+)
                IconButton(
                    onClick = { onQuantityChanged(item.id, item.quantity + 1) },
                    modifier = Modifier
                        .size(24.dp)
                ) {
                    Text("+", fontSize = 16.sp, color = Color.Black)
                }

                // Hiển thị số lượng
                Text(
                    text = String.format(Locale.getDefault(), "%02d", item.quantity),
                    modifier = Modifier.padding(horizontal = 12.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                // Nút giảm (-)
                IconButton(
                    onClick = {
                        if (item.quantity > 1) {
                            onQuantityChanged(item.id, item.quantity - 1)
                        }
                    }, modifier = Modifier
                        .size(24.dp)
                ) {
                    Text("−", fontSize = 16.sp, color = Color.Black)
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CartScreenPreview() {
    CartScreen()
}
