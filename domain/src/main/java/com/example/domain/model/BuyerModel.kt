package com.example.domain.model

/**
 * Data class representing information about a buyer.
 *
 * @property phoneNumber A [String] representing the phone number of the buyer.
 * @property mailAddress A [String] representing the email address of the buyer.
 */
data class BuyerModel(
    var phoneNumber: String = "",
    var mailAddress: String = ""
)
