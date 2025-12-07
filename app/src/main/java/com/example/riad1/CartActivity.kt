package com.example.riad1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        displayCart()
    }

    private fun displayCart() {
        val container = findViewById<LinearLayout>(R.id.cartItemsContainer)
        val tvTotal = findViewById<TextView>(R.id.tvTotalPrice)

        container.removeAllViews()

        for (item in CartManager.items.toList()) {
            val view = layoutInflater.inflate(R.layout.cart_item, container, false)

            val ivDish     = view.findViewById<ImageView>(R.id.ivDish)
            val tvDishName = view.findViewById<TextView>(R.id.tvDishName)
            val tvDishInfo = view.findViewById<TextView>(R.id.tvDishInfo)
            val tvQuantity = view.findViewById<TextView>(R.id.tvQuantity)
            val btnPlus    = view.findViewById<Button>(R.id.btnPlus)
            val btnMinus   = view.findViewById<Button>(R.id.btnMinus)

            ivDish.setImageResource(item.imageRes)
            tvDishName.text = item.title
            tvQuantity.text = item.quantity.toString()
            tvDishInfo.text = "${item.quantity} × ${item.price}€"

            btnPlus.setOnClickListener {
                item.quantity++
                displayCart()
            }

            btnMinus.setOnClickListener {
                if (item.quantity > 1) {
                    item.quantity--
                } else {
                    CartManager.items.remove(item)
                }
                displayCart()
            }

            container.addView(view)
        }

        updateTotal(tvTotal)
    }

    private fun updateTotal(tvTotal: TextView) {
        val total = CartManager.items.sumOf { it.price * it.quantity }
        tvTotal.text = "${total}€"
    }

    // 🔹 appelé par android:onClick dans le XML
    fun goToPayment(view: View) {
        val intent = Intent(this, PaymentActivity::class.java)
        startActivity(intent)
    }
}
