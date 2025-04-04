package com.example.kotlin.Lab5

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin.R

data class ProductItem(val name: String, val imageRes: Int)

@Composable
fun ProductScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // List of product categories with images
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                CategorySection(
                    "Vegetables", listOf(
                        ProductItem("Potato", R.drawable.potato),
                        ProductItem("Carrot", R.drawable.carrot),
                        ProductItem("Onion", R.drawable.onion)
                    )
                )
            }

            item {
                CategorySection(
                    "Grocery", listOf(
                        ProductItem("Rice", R.drawable.rice),
                        ProductItem("Buckwheat", R.drawable.buckwheat),
                        ProductItem("Cous Cous", R.drawable.couscous)
                    )
                )
            }

            item {
                CategorySection(
                    "For home", listOf(
                        ProductItem("Rug", R.drawable.rug),
                        ProductItem("Screwdriver", R.drawable.screwdriver),
                        ProductItem("Towels", R.drawable.towels)
                    )
                )
            }

            item {
                CategorySection(
                    "Vegetables", listOf(
                        ProductItem("Potato", R.drawable.potato),
                        ProductItem("Carrot", R.drawable.carrot),
                        ProductItem("Onion", R.drawable.onion)
                    )
                )
            }
        }
    }
}

@Composable
fun CategorySection(title: String, products: List<ProductItem>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 8.dp)
        )

        LazyRow {
            items(products) { product ->
                ProductCard(product)
            }
        }
    }
}

@Composable
fun ProductCard(product: ProductItem) {
    LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(8.dp)
            .size(140.dp, 160.dp)
            .clickable { showDialog = true },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Product Information") },
            text = { Text("Product: ${product.name}") },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductScreen() {
    ProductScreen()
}