package com.example.riad1

object CartManager {

    // 🔥 Nouveau champ : numéro de réservation
    var reservationNumber: String? = null

    data class CartItem(
        val title: String,
        val price: Int,
        val imageRes: Int,
        var quantity: Int = 1
    )

    val items = mutableListOf<CartItem>()

    fun addItem(title: String, price: Int, imageRes: Int) {
        val existing = items.find { it.title == title }

        if (existing != null) {
            existing.quantity++
        } else {
            items.add(CartItem(title, price, imageRes))
        }
    }

    fun clear() {
        items.clear()
        reservationNumber = null   // on efface aussi le numéro quand on vide le panier
    }
}
