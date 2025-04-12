package com.example.kotlin.ASM.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.kotlin.ASM.screen.CartItem

/**
 * Singleton object to store cart data across the app
 */
object CartDataStore {
    // Mutable state to hold cart items
    private val _cartItems = mutableStateOf<List<CartItem>>(emptyList())

    /**
     * Composable function to observe cart items
     */
    @Composable
    fun observeCartItems(): State<List<CartItem>> {
        return remember { _cartItems }
    }

    // We can add methods here if needed for non-composable contexts in the future

    /**
     * Add a new product to the cart
     */
    fun addProduct(newProduct: CartItem) {
        val existingItem = _cartItems.value.find { it.id == newProduct.id }

        _cartItems.value = if (existingItem != null) {
            // Update quantity if item already exists
            _cartItems.value.map {
                if (it.id == newProduct.id) {
                    it.copy(quantity = it.quantity + newProduct.quantity)
                } else {
                    it
                }
            }
        } else {
            // Add new item
            _cartItems.value + newProduct
        }
    }

    /**
     * Update the quantity of an item in the cart
     */
    fun updateItemQuantity(id: Int, newQuantity: Int) {
        val safeNewQuantity = maxOf(1, newQuantity)
        _cartItems.value = _cartItems.value.map {
            if (it.id == id) it.copy(quantity = safeNewQuantity) else it
        }
    }

    /**
     * Remove an item from the cart
     */
    fun removeItem(id: Int) {
        _cartItems.value = _cartItems.value.filter { it.id != id }
    }

    /**
     * Clear the cart
     */
    fun clearCart() {
        _cartItems.value = emptyList()
    }
}