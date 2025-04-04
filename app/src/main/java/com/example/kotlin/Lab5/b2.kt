package com.example.kotlin.Lab5

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin.R

data class Product(val id: Int, val imageRes: Int, var isFlipped: Boolean = false)

@Composable
fun MatchingGameScreen() {
    var items by remember { mutableStateOf(sampleImages().shuffled()) }
    var selectedIndices by remember { mutableStateOf<List<Int>>(emptyList()) }
    var showWinDialog by remember { mutableStateOf(false) }

    fun handleItemClick(index: Int) {
        if (index in selectedIndices || items[index].isFlipped) return

        // Cập nhật trạng thái lật của thẻ
        items = items.mapIndexed { i, product ->
            if (i == index) product.copy(isFlipped = true) else product
        }

        selectedIndices = selectedIndices + index

        if (selectedIndices.size == 2) {
            items = checkMatchingPairs(items, selectedIndices)
            selectedIndices = emptyList()
            showWinDialog = checkWinCondition(items)
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        itemsIndexed(items) { index, item ->
            ItemProduct(product = item, onClick = { handleItemClick(index) })
        }
    }

    if (showWinDialog) {
        AlertDialog(
            onDismissRequest = { showWinDialog = false },
            title = { Text("Chúc mừng!") },
            text = { Text("Bạn đã ghép xong tất cả các cặp hình!") },
            confirmButton = {
                TextButton(onClick = {
                    showWinDialog = false
                    items = sampleImages().shuffled()
                }) { Text("Chơi lại") }
            }
        )
    }
}

fun checkMatchingPairs(items: List<Product>, selectedIndices: List<Int>): List<Product> {
    val (firstIndex, secondIndex) = selectedIndices
    val first = items[firstIndex]
    val second = items[secondIndex]

    return if (first.imageRes == second.imageRes) {
        // Nếu trùng, xóa cặp này khỏi danh sách
        items.filterNot { it.imageRes == first.imageRes }
    } else {
        // Nếu không trùng, úp lại các thẻ đã chọn
        items.mapIndexed { index, product ->
            if (index == firstIndex || index == secondIndex) product.copy(isFlipped = false)
            else product
        }
    }
}

fun checkWinCondition(items: List<Product>): Boolean {
    return items.isEmpty()
}

@Composable
fun ItemProduct(product: Product, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(visible = product.isFlipped) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
        }
    }
}

fun sampleImages(): List<Product> {
    return listOf(
        Product(1, R.drawable.buckwheat),
        Product(2, R.drawable.buckwheat),
        Product(3, R.drawable.carrot),
        Product(4, R.drawable.carrot),
        Product(5, R.drawable.couscous),
        Product(6, R.drawable.couscous)
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewMatchingGameScreen() {
    MatchingGameScreen()
}
