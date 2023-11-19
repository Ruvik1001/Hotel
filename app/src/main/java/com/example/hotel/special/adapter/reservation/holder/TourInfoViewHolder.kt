package com.example.hotel.special.adapter.reservation.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ReservationTourModel
import com.example.hotel.R

class TourInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(reservationTourModel: ReservationTourModel) {
        itemView.findViewById<TextView>(R.id.from).setText("${reservationTourModel.departure}")
        itemView.findViewById<TextView>(R.id.to).setText("${reservationTourModel.arrival_country}")
        itemView.findViewById<TextView>(R.id.date).setText("${reservationTourModel.tour_date_start} - ${reservationTourModel.tour_date_stop}")
        itemView.findViewById<TextView>(R.id.night_count).setText("${reservationTourModel.number_of_nights}")
        itemView.findViewById<TextView>(R.id.hotel).setText("${reservationTourModel.hotel_name}")
        itemView.findViewById<TextView>(R.id.room).setText("${reservationTourModel.room}")
        itemView.findViewById<TextView>(R.id.type).setText("${reservationTourModel.nutrition}")
    }
}