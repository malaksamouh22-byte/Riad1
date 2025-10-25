package com.example.riad1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_items")
data class OrderItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dishName: String,
    val price: Int,          // prix d’une unité, ex: 30
    val quantity: Int,       // ex: 2
    val imageRes: Int,       // ex: R.drawable.tajine
    val orderDate: Long      // pour l’historique (timestamp)
)
