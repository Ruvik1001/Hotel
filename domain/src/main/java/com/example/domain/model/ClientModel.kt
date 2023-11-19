package com.example.domain.model

data class ClientModel(
    val clientLabel: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var dateOfBirth: String = "",
    var citizenship: String = "",
    var passportNumber: String = "",
    var passportExpirationDate: String = ""
)
