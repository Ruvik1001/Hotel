package com.example.hotel.special.adapter.reservation.holder

import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.BuyerModel
import com.example.hotel.R
import com.example.hotel.special.interfaces.OnEditedCallback
import com.example.hotel.special.watcher.CustomPhoneNumberWatcher

/**
 * ViewHolder class for displaying and editing buyer information in the reservation adapter.
 *
 * @param itemView The view for the ViewHolder.
 * @param onEditedCallback Callback interface for handling user interactions and edits.
 */
class BuyerInfoViewHolder(itemView: View, private val onEditedCallback: OnEditedCallback) : RecyclerView.ViewHolder(itemView) {

    /**
     * Binds data to the ViewHolder.
     *
     * @param buyerModel The buyer information to bind.
     */
    fun bind(buyerModel: BuyerModel) {
        itemView.findViewById<TextView>(R.id.label).setText("Информация о покупателе")

        val editTextPhoneNumber = itemView.findViewById<EditText>(R.id.phoneNumber)
        editTextPhoneNumber.addTextChangedListener(CustomPhoneNumberWatcher())
        editTextPhoneNumber.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                buyerModel.phoneNumber = editTextPhoneNumber.text.toString()
                onEditedCallback.updatePhone(buyerModel, itemView.findViewById<TableLayout>(R.id.filed_phone))
            }
        }

        val editTextMailAddress = itemView.findViewById<EditText>(R.id.mail)
        editTextMailAddress.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                buyerModel.mailAddress = editTextMailAddress.text.toString()
                onEditedCallback.updateMail(buyerModel, itemView.findViewById<TableLayout>(R.id.filed_mail))
            }
        }
    }
}
