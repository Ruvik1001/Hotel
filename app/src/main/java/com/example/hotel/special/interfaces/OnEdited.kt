package com.example.hotel.special.interfaces

import android.widget.TableLayout
import com.example.domain.model.BuyerModel
import com.example.domain.model.ClientModel

interface OnEdited {
    fun updatePhone(
        buyerModel: BuyerModel,
        viewPhone: TableLayout,
    )
    fun updateMail(
        buyerModel: BuyerModel,
        viewMail: TableLayout
    )

    fun updateName(clientModel: ClientModel, viewMail: TableLayout)
    fun updateLastName(clientModel: ClientModel, viewMail: TableLayout)
    fun updateDateOfBirth(clientModel: ClientModel, viewMail: TableLayout)
    fun updatePassportNumber(clientModel: ClientModel, viewMail: TableLayout)
    fun updatePassportExpirationDate(clientModel: ClientModel, viewMail: TableLayout)
    fun updateCitizenship(clientModel: ClientModel, viewMail: TableLayout)

    fun updatePrice(cost: Int)
}