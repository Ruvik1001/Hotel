package com.example.hotel.special.adapter.reservation.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ReservationHotelModel
import com.example.hotel.R

class HotelInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(reservationHotelModel: ReservationHotelModel) {
        itemView.findViewById<TextView>(R.id.rating).setText("${reservationHotelModel.hotel_rating} ${reservationHotelModel.rating_name}")
        itemView.findViewById<TextView>(R.id.hotel_name).setText("${reservationHotelModel.hotel_name}")
        itemView.findViewById<TextView>(R.id.hotel_address).setText("${reservationHotelModel.hotel_adress}")
    }
}