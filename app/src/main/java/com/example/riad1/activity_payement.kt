package com.example.riad1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payement)

        // 🔹 Enregistrer la commande dans la base
        saveOrderToDatabase()

        // 🔹 Bouton "Retour à l’accueil"
        val btnBackHome = findViewById<Button>(R.id.btnBackHome)
        btnBackHome.setOnClickListener {
            // Vider le panier en mémoire
            CartManager.items.clear()

            // Revenir à la page d’accueil
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    private fun saveOrderToDatabase() {
        val db = AppDatabase.getInstance(applicationContext)
        val dao = db.orderDao()
        val timestamp = System.currentTimeMillis()

        // 🔹 On transforme les éléments du panier en entités pour la DB
        val entityList: List<OrderItemEntity> = CartManager.items.map { item ->
            OrderItemEntity(
                dishName = item.title,      // nom du plat
                quantity = item.quantity,   // quantité
                price = item.price,         // prix unitaire
                imageRes = item.imageRes,   // image
                orderDate = timestamp       // même date pour toute la commande
            )
        }

        // 🔹 Insertion en base sur un thread IO
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertItems(entityList)
        }
    }
}
