package com.example.kotlin.ASM.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.kotlin.ASM.data.CartDataStore
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
fun CartScreen(
    newProduct: CartItem? = null,
    onCheckoutClick: (totalAmount: Float) -> Unit,
    onBackClick: () -> Unit = {}
) {
    // Add new product to cart if provided
    if (newProduct != null) {
        CartDataStore.addProduct(newProduct)
    }

    // Get cart items from the data store using the observable function
    val cartItems by CartDataStore.observeCartItems()

    var promoCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp)
    ) {
        CartHeader(onBackClick = onBackClick)

        Spacer(modifier = Modifier.height(20.dp))

        // Use Box with weight to allow scrolling of cart items
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            CartItemsList(
                cartItems = cartItems,
                onQuantityChanged = { id, newQuantity ->
                    CartDataStore.updateItemQuantity(id, newQuantity)
                },
                onRemoveClicked = { id ->
                    CartDataStore.removeItem(id)
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        PromoCodeInput(
            promoCode = promoCode,
            onPromoCodeChange = { promoCode = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        CartFooter(
            cartItems = cartItems,
            onCheckoutClick = onCheckoutClick
        )
    }
}

@Composable
fun CartHeader(onBackClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "Back",
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = "My cart",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        Box(modifier = Modifier.size(20.dp)) // Empty box to balance layout
    }
}

@Composable
fun CartItemsList(
    cartItems: List<CartItem>,
    onQuantityChanged: (Int, Int) -> Unit,
    onRemoveClicked: (Int) -> Unit
) {
    if (cartItems.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Your cart is empty",
                fontSize = 18.sp,
                color = Color.Gray
            )
        }
    } else {
        // Use LazyColumn for scrollable content
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(cartItems) { item ->
                CartItemRow(
                    item = item,
                    onQuantityChanged = onQuantityChanged,
                    onRemoveClicked = onRemoveClicked
                )
            }
        }
    }
}

@Composable
fun PromoCodeInput(
    promoCode: String,
    onPromoCodeChange: (String) -> Unit
) {
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
            onValueChange = onPromoCodeChange,
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
            onClick = { },
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color.Black)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "Apply",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun CartFooter(
    cartItems: List<CartItem>,
    onCheckoutClick: (Float) -> Unit
) {
    // Calculate total from the current cart items
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
        onClick = { onCheckoutClick(total.toFloat()) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        enabled = cartItems.isNotEmpty()
    ) {
        Text(
            text = "Check out",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
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
        Image(
            painter = painterResource(id = item.imageRes.takeIf { it != 0 }
                ?: R.drawable.img_stand),
            contentDescription = item.name,
            modifier = Modifier
                .size(80.dp)
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
                        contentDescription = "Remove",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

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
                IconButton(
                    onClick = { onQuantityChanged(item.id, item.quantity + 1) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Text("+", fontSize = 16.sp, color = Color.Black)
                }

                Text(
                    text = String.format(Locale.getDefault(), "%02d", item.quantity),
                    modifier = Modifier.padding(horizontal = 12.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                IconButton(
                    onClick = {
                        if (item.quantity > 1) {
                            onQuantityChanged(item.id, item.quantity - 1)
                        }
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Text("−", fontSize = 16.sp, color = Color.Black)
                }
            }
        }
    }
}

// Helper functions moved to CartDataStore

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CartScreenPreview() {
    CartScreen(
        newProduct = null,
        onCheckoutClick = {}
    )
}