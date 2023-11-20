package com.example.hotel.special.adapter.reservation.holder

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ClientAddModel
import com.example.hotel.R

/**
 * ViewHolder class for displaying the "Add Tourist" button in the reservation adapter.
 *
 * @param itemView The view for the ViewHolder.
 */
class ClientAddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * Binds data to the ViewHolder.
     *
     * @param clientAddModel The model containing information for the "Add Tourist" button.
     * @param onClick Callback function to handle click events on the "Add Tourist" button.
     */
    fun bind(clientAddModel: ClientAddModel, onClick: () -> Unit) {
        itemView.findViewById<TextView>(R.id.add_turist_text).setText("${clientAddModel.text}")
        itemView.findViewById<ImageButton>(R.id.btn_add_client).setOnClickListener {
            onClick()
        }
    }
}
