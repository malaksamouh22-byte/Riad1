package com.example.riad1

object CartManager {

    // Un élément dans le panier
    data class CartItem(
        val title: String,      // nom du plat
        val price: Int,         // prix unitaire
        val imageRes: Int,      // image du plat
        var quantity: Int = 1   // quantité
    )

    // ⚠️ PAS de "private" ici → accessible depuis CartActivity
    val items: MutableList<CartItem> = mutableListOf()

    // Ajouter un plat au panier
    fun addItem(title: String, price: Int, imageRes: Int) {
        // si le plat existe déjà, on augmente la quantité
        val existing = items.find { it.title == title }
        if (existing != null) {
            existing.quantity++
        } else {
            items.add(CartItem(title, price, imageRes, 1))
        }
    }

    fun clear() {
        items.clear()
    }
}
