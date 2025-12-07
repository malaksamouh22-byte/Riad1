package com.example.riad1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

// Tu peux garder l'EditText si tu veux afficher le numéro généré dedans
        val etReservation = findViewById<EditText>(R.id.etReservationNumber)
        val btnEnter = findViewById<Button>(R.id.btnEnter)
        val btnHistory = findViewById<Button>(R.id.btnHistory)

// ✅ BDD
        val db = AppDatabase.getInstance(this)
        val dao = db.orderDao()

// ▶️ Quand on clique sur "Réserver une table"
        btnEnter.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
// 1) On récupère le dernier numéro
                val lastNumber = dao.getLastReservationNumber() ?: 0

// 2) On calcule le nouveau numéro
                val newNumber = lastNumber + 1

// 3) On le garde dans le panier global
                CartManager.reservationNumber = newNumber.toString()

// 4) On revient sur le thread UI pour démarrer le menu
                withContext(Dispatchers.Main) {
// Facultatif : afficher dans le champ ou en Toast
                    etReservation.setText(newNumber.toString())

                    Toast.makeText(
                        this@MainActivity,
                        "Votre numéro de réservation : $newNumber",
                        Toast.LENGTH_LONG
                    ).show()

                    val intent = Intent(this@MainActivity, MenuActivity::class.java)
                    startActivity(intent)
                }
            }
        }

// ▶️ Bouton Historique
        btnHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}