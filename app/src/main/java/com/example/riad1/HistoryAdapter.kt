package com.example.riad1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Représente UNE réservation avec plusieurs plats
data class ReservationHistoryGroup(
    val reservationNumber: String,
    val orderDate: Long,
    val items: List<OrderItemEntity>
)

class HistoryAdapter(
    private var groups: List<ReservationHistoryGroup>
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivDish: ImageView = itemView.findViewById(R.id.ivDishHistory)
        val tvTitle: TextView = itemView.findViewById(R.id.tvDishNameHistory)
        val tvDetails: TextView = itemView.findViewById(R.id.tvDishInfoHistory)
        val tvTotal: TextView = itemView.findViewById(R.id.tvReservationHistory)
        val tvDate: TextView = itemView.findViewById(R.id.tvDateHistory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val group = groups[position]

        // 1) image = celle du premier plat de la réservation (juste pour illustrer)
        val firstItem = group.items.firstOrNull()
        if (firstItem != null) {
            holder.ivDish.setImageResource(firstItem.imageRes)
        }

        // 2) Titre : "Réservation : X"
        holder.tvTitle.text = "Réservation : ${group.reservationNumber}"

        // 3) Détails : tous les plats de cette réservation
        //    Exemple :
        //    Tajine aux pruneaux – 2 × 30€
        //    Jawhara – 1 × 12€
        val details = group.items.joinToString(separator = "\n") { item ->
            "${item.dishName} – ${item.quantity} × ${item.price}€"
        }
        holder.tvDetails.text = details

        // 4) Total de la réservation
        val total = group.items.sumOf { it.quantity * it.price }
        holder.tvTotal.text = "Total : ${total}€"

        // 5) Date formatée
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        holder.tvDate.text = dateFormat.format(Date(group.orderDate))
    }

    override fun getItemCount(): Int = groups.size

    fun setItems(newGroups: List<ReservationHistoryGroup>) {
        groups = newGroups
        notifyDataSetChanged()
    }
}
