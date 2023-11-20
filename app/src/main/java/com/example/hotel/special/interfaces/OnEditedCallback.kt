package com.example.hotel.special.interfaces

import android.widget.TableLayout
import com.example.domain.model.BuyerModel
import com.example.domain.model.ClientModel

/**
 * Interface for callbacks when editing buyer and client information.
 */
interface OnEditedCallback {
    /**
     * Callback to update the phone number.
     *
     * @param buyerModel The buyer model containing the phone number.
     * @param viewPhone The view associated with the phone number.
     */
    fun updatePhone(
        buyerModel: BuyerModel,
        viewPhone: TableLayout,
    )

    /**
     * Callback to update the email address.
     *
     * @param buyerModel The buyer model containing the email address.
     * @param viewMail The view associated with the email address.
     */
    fun updateMail(
        buyerModel: BuyerModel,
        viewMail: TableLayout
    )

    /**
     * Callback to update the client's first name.
     *
     * @param clientModel The client model containing the first name.
     * @param viewMail The view associated with the first name.
     */
    fun updateName(clientModel: ClientModel, viewMail: TableLayout)

    /**
     * Callback to update the client's last name.
     *
     * @param clientModel The client model containing the last name.
     * @param viewMail The view associated with the last name.
     */
    fun updateLastName(clientModel: ClientModel, viewMail: TableLayout)

    /**
     * Callback to update the client's date of birth.
     *
     * @param clientModel The client model containing the date of birth.
     * @param viewMail The view associated with the date of birth.
     */
    fun updateDateOfBirth(clientModel: ClientModel, viewMail: TableLayout)

    /**
     * Callback to update the client's passport number.
     *
     * @param clientModel The client model containing the passport number.
     * @param viewMail The view associated with the passport number.
     */
    fun updatePassportNumber(clientModel: ClientModel, viewMail: TableLayout)

    /**
     * Callback to update the client's passport expiration date.
     *
     * @param clientModel The client model containing the passport expiration date.
     * @param viewMail The view associated with the passport expiration date.
     */
    fun updatePassportExpirationDate(clientModel: ClientModel, viewMail: TableLayout)

    /**
     * Callback to update the client's citizenship.
     *
     * @param clientModel The client model containing the citizenship.
     * @param viewMail The view associated with the citizenship.
     */
    fun updateCitizenship(clientModel: ClientModel, viewMail: TableLayout)

    /**
     * Callback to update the reservation price.
     *
     * @param cost The updated reservation cost.
     */
    fun updatePrice(cost: Int)
}
