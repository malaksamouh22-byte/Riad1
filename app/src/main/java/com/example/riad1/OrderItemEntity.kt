package com.example.riad1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_items")
data class OrderItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val dishName: String,
    val quantity: Int,
    val price: Int,
    val imageRes: Int,
    val orderDate: Long,

    // 🔹 Numéro de réservation associé à cette commande
    val reservationNumber: String
)
