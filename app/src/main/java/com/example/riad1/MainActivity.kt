package com.example.riad1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ✅ Récupération des vues
        val btnEnter = findViewById<Button>(R.id.btnEnter)
        val etReservationNumber = findViewById<EditText>(R.id.etReservationNumber)

        // ✅ Quand on clique sur "Réserver une table"
        btnEnter.setOnClickListener {
            val numero = etReservationNumber.text.toString().trim()

            if (numero.isEmpty()) {
                // Champ vide → message d’erreur
                Toast.makeText(
                    this,
                    "Veuillez entrer votre numéro de réservation",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Ici tu peux utiliser le numéro (l'envoyer à l'autre activité, etc.)
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("reservation_number", numero)
                startActivity(intent)
            }
        }
    }
}
