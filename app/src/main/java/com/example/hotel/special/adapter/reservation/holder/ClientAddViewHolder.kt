package com.example.hotel.special.adapter.reservation.holder

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ClientAddModel
import com.example.hotel.R

class ClientAddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(clientAddModel: ClientAddModel, onClick: ()->Unit) {
        itemView.findViewById<TextView>(R.id.add_turist_text).setText("${clientAddModel.text}")
        itemView.findViewById<ImageButton>(R.id.btn_add_client).setOnClickListener {
            onClick()
        }
    }
}