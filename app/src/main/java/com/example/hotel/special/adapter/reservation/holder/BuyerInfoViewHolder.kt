package com.example.hotel.special.adapter.reservation.holder

import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.BuyerModel
import com.example.hotel.R
import com.example.hotel.special.interfaces.OnEdited
import com.example.hotel.special.watcher.CustomPhoneNumberWatcher

class BuyerInfoViewHolder(itemView: View, private val onEdited: OnEdited) : RecyclerView.ViewHolder(itemView) {

    fun bind(buyerModel: BuyerModel) {
        itemView.findViewById<TextView>(R.id.label).setText("Информация о покупателе")
        val editTextPhoneNumber = itemView.findViewById<EditText>(R.id.phoneNumber)
        editTextPhoneNumber.addTextChangedListener(CustomPhoneNumberWatcher())
        editTextPhoneNumber.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                buyerModel.phoneNumber = editTextPhoneNumber.text.toString()
                onEdited.updatePhone(buyerModel, itemView.findViewById<TableLayout>(R.id.filed_phone))
            }
        }
        val editTextMailAddress = itemView.findViewById<EditText>(R.id.mail)
        editTextMailAddress.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                buyerModel.mailAddress = editTextMailAddress.text.toString()
                onEdited.updateMail(buyerModel, itemView.findViewById<TableLayout>(R.id.filed_mail))
            }
        }
    }
}