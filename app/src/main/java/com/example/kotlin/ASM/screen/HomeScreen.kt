package com.example.kotlin.ASM.screen

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin.R

// Data classes
data class Category(val name: String, val icon: Int)
data class Product(
    val name: String,
    val price: Double,
    val imageRes: Int,
    val isFavorite: Boolean = false
)

@Composable
fun HomeScreen() {
    // Get context at composable function level
    // This is used in the ProductItem click handler
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier.size(24.dp)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Make home", fontSize = 12.sp, color = Color.Gray)
                Text("BEAUTIFUL", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        val categories = listOf(
            Category("Popular", R.drawable.ic_star),
            Category("Chair", R.drawable.ic_chair),
            Category("Table", R.drawable.ic_table),
            Category("Armchair", R.drawable.ic_armchair),
            Category("Bed", R.drawable.ic_bed),
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(categories.size) { index ->
                val category = categories[index]
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (index == 0) Color.Black else Color.White)
                            .padding(11.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = category.icon),
                            contentDescription = category.name,
                            modifier = Modifier.size(28.dp),
                            tint = if (index == 0) Color.White else Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(category.name, fontSize = 12.sp, textAlign = TextAlign.Center)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        val products = listOf(
            Product("Black Simple Lamp", 12.00, R.drawable.img_lamp),
            Product("Minimal Stand", 25.00, R.drawable.img_stand),
            Product("Coffee Chair", 20.00, R.drawable.img_chair),
            Product("Simple Desk", 50.00, R.drawable.img_desk)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(products) { product ->
                // Use context from outer scope
                ProductItem(product = product) {
                    // Using context directly from the outer scope
                    try {
                        val intent = Intent(
                            context,
                            Class.forName("com.example.kotlin.ASM.screen.ProductDetailScreen")
                        ).apply {
                            putExtra("name", product.name)
                            putExtra("price", product.price)
                            putExtra("imageRes", product.imageRes)

                            // Log để debug
                            Log.d(
                                "HomeScreen",
                                "Launching product: ${product.name}, image: ${product.imageRes}"
                            )
                        }
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Log.e("HomeScreen", "Error launching ProductDetailScreen", e)
                    }
                }
            }

        }

    }
}

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    var isFavorite by remember { mutableStateOf(product.isFavorite) }

    Column(
        modifier = Modifier
            .height(253.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null // Material 3 handles ripple effects automatically
            ) {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )

            IconButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0x60606066))
                    .padding(5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_shopping_bag),
                    contentDescription = "Add to cart",
                    tint = if (isFavorite) Color.Black else Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            Text(product.name, fontSize = 14.sp, maxLines = 1)
            Text("$ ${product.price}", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
