package com.example.riad1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payement)

        // Enregistrement de la commande
        saveOrderToDatabase()

        val btnBackHome = findViewById<Button>(R.id.btnBackHome)
        btnBackHome.setOnClickListener {
            // vider le panier + le numéro de réservation
            CartManager.items.clear()
            CartManager.reservationNumber = null

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    private fun saveOrderToDatabase() {
        val reservationNumber = CartManager.reservationNumber

        // Sécurité : si pas de numéro, on ne sauvegarde pas
        if (reservationNumber.isNullOrBlank()) {
            Toast.makeText(
                this,
                "Numéro de réservation manquant, commande non enregistrée.",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val db = AppDatabase.getInstance(this)
        val dao = db.orderDao()
        val timestamp = System.currentTimeMillis()

        CoroutineScope(Dispatchers.IO).launch {
            val entities = CartManager.items.map { item ->
                OrderItemEntity(
                    dishName = item.title,
                    quantity = item.quantity,
                    price = item.price,
                    imageRes = item.imageRes,
                    orderDate = timestamp,
                    reservationNumber = CartManager.reservationNumber ?:"Inconnue"
                )
            }
            dao.insertItems(entities)
        }
    }
}
