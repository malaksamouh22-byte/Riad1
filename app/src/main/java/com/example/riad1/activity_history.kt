package com.example.riad1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {

    private lateinit var adapter: HistoryAdapter
    private lateinit var tvEmptyHistory: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val rvHistory = findViewById<RecyclerView>(R.id.rvHistory)
        tvEmptyHistory = findViewById(R.id.tvEmptyHistory)
        val btnClear = findViewById<Button>(R.id.btnClearHistory)

        rvHistory.layoutManager = LinearLayoutManager(this)
        adapter = HistoryAdapter(emptyList())
        rvHistory.adapter = adapter

        // Charger les données
        loadHistory()

        // Vider l'historique
        btnClear.setOnClickListener {
            clearHistory()
        }
    }

    private fun loadHistory() {
        val db = AppDatabase.getInstance(this)
        val dao = db.orderDao()

        CoroutineScope(Dispatchers.IO).launch {
            val allItems = dao.getAllOrders()   // Liste<OrderItemEntity>

            // 👉 Regrouper par (reservationNumber, orderDate)
            val groups = allItems
                .groupBy { Pair(it.reservationNumber ?: "?", it.orderDate) }
                .map { (key, items) ->
                    val (resNum, date) = key
                    ReservationHistoryGroup(
                        reservationNumber = resNum,
                        orderDate = date,
                        items = items
                    )
                }
                .sortedByDescending { it.orderDate }

            withContext(Dispatchers.Main) {
                adapter.setItems(groups)
                tvEmptyHistory.visibility =
                    if (groups.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    private fun clearHistory() {
        val db = AppDatabase.getInstance(this)
        val dao = db.orderDao()

        CoroutineScope(Dispatchers.IO).launch {
            dao.clearAll()
            withContext(Dispatchers.Main) {
                adapter.setItems(emptyList())
                tvEmptyHistory.visibility = View.VISIBLE
            }
        }
    }
}
