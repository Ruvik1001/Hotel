package com.example.hotel.special.adapter.reservation.holder

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ReservationCostModel
import com.example.hotel.R
import com.example.hotel.special.interfaces.OnEdited
import java.text.NumberFormat
import java.util.Locale

class CostInfoViewHolder(itemView: View, private val onEdited: OnEdited) : RecyclerView.ViewHolder(itemView) {
    fun bind(context: Context, reservationCostModel: ReservationCostModel) {
        val result = reservationCostModel.tour_price + reservationCostModel.fuel_charge + reservationCostModel.service_charge
        val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())

        val currencyRus = context.getString(R.string.currency_rus)

        itemView.findViewById<TextView>(R.id.tour).text = "${numberFormat.format(reservationCostModel.tour_price).replace(","," ")} $currencyRus"
        itemView.findViewById<TextView>(R.id.fuel).text = "${numberFormat.format(reservationCostModel.fuel_charge).replace(","," ")} $currencyRus"
        itemView.findViewById<TextView>(R.id.service).text = "${numberFormat.format(reservationCostModel.service_charge).replace(","," ")} $currencyRus"
        itemView.findViewById<TextView>(R.id.result).text = "${numberFormat.format(result).replace(","," ")} $currencyRus"

        onEdited.updatePrice(result)
    }
}
