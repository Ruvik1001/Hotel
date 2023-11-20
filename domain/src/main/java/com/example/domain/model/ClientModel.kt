package com.example.domain.model

/**
 * Data class representing information about a client.
 *
 * @property clientLabel A [String] representing a label for the client.
 * @property firstName A [String] representing the first name of the client.
 * @property lastName A [String] representing the last name of the client.
 * @property dateOfBirth A [String] representing the date of birth of the client.
 * @property citizenship A [String] representing the citizenship of the client.
 * @property passportNumber A [String] representing the passport number of the client.
 * @property passportExpirationDate A [String] representing the expiration date of the client's passport.
 */
data class ClientModel(
    val clientLabel: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var dateOfBirth: String = "",
    var citizenship: String = "",
    var passportNumber: String = "",
    var passportExpirationDate: String = ""
)
