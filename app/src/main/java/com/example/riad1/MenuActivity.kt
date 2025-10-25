package com.example.riad1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)   // juste ça, pas de ViewCompat
    }

    // appelée par android:onClick="onAddToCart" dans activity_menu.xml
    fun onAddToCart(view: View) {
        var dishName = ""
        var price = 0
        var imageRes = R.drawable.tajine

        when (view.id) {
            R.id.btnAdd1 -> {
                dishName = "Tajine aux pruneaux"
                price = 30
                imageRes = R.drawable.tajine
            }
            R.id.btnAdd2 -> {
                dishName = "Pastilla au poulet"
                price = 25
                imageRes = R.drawable.bastillap
            }
            R.id.btnAdd3 -> {
                dishName = "Pastilla fruits de mer"
                price = 45
                imageRes = R.drawable.bastilla
            }
            R.id.btnAdd4 -> {
                dishName = "Rfissa traditionnelle"
                price = 30
                imageRes = R.drawable.trid
            }
            R.id.btnAdd5 -> {
                dishName = "Jawhara"
                price = 12
                imageRes = R.drawable.jawhara
            }
        }

        // ajouter au panier global
        CartManager.addItem(dishName, price, imageRes)

        // petit message
        Toast.makeText(this, "$dishName ajoutée au panier", Toast.LENGTH_SHORT).show()

        // ouvrir la page panier
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }
}
