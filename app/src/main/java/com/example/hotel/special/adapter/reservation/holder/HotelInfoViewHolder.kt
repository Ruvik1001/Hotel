package com.example.hotel.special.adapter.reservation.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ReservationHotelModel
import com.example.hotel.R

/**
 * ViewHolder class for displaying hotel information in the reservation adapter.
 *
 * @param itemView The view for the ViewHolder.
 */
class HotelInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Binds data to the ViewHolder.
     *
     * @param reservationHotelModel The model containing hotel information.
     */
    fun bind(reservationHotelModel: ReservationHotelModel) {
        itemView.findViewById<TextView>(R.id.rating).setText("${reservationHotelModel.hotel_rating} ${reservationHotelModel.rating_name}")
        itemView.findViewById<TextView>(R.id.hotel_name).setText("${reservationHotelModel.hotel_name}")
        itemView.findViewById<TextView>(R.id.hotel_address).setText("${reservationHotelModel.hotel_adress}")
    }
}
