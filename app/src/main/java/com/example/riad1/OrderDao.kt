package com.example.riad1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {

    // INSERT d’une LISTE de lignes
    @Insert
    fun insertItems(items: List<OrderItemEntity>)

    @Query("SELECT * FROM order_items ORDER BY orderDate DESC")
    fun getAllOrders(): List<OrderItemEntity>

    @Query("DELETE FROM order_items")
    fun clearAll()
    @Query("SELECT MAX(reservationNumber) FROM order_items ")
    fun getLastReservationNumber(): Int?
}
