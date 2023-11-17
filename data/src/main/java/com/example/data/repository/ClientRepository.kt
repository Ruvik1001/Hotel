package com.example.data.repository

import com.example.domain.model.ClientModel
import com.example.domain.repository.ClientRepository
import kotlin.random.Random

const val FIRST_NAME_ERROR_KEY = "FIRST_NAME_ERROR"
const val LAST_NAME_ERROR_KEY = "LAST_NAME_ERROR"
const val DATE_OF_BIRTH_ERROR_KEY = "DATE_OF_BIRTH_ERROR"
const val PASSPORT_NUMBER_ERROR_KEY = "PASSPORT_NUMBER_ERROR"
const val PASSPORT_EXPIRATION_DATE_ERROR_KEY = "PASSPORT_EXPIRATION_DATE_ERROR"

class ClientRepository: ClientRepository {
    private fun validateField(value: String, pattern: String): Boolean {
        return Regex(pattern).matches(value) && value.isNotBlank()
    }

    override fun validateClient(client: ClientModel): List<String> {
        val errors = mutableListOf<String>()

        if (!validateField(client.firstName, "^[A-Za-zА-Яа-я\\s'-]+$")) {
            errors.add(FIRST_NAME_ERROR_KEY)
        }

        if (!validateField(client.lastName, "^[A-Za-zА-Яа-я\\s'-]+$")) {
            errors.add(LAST_NAME_ERROR_KEY)
        }

        if (!validateField(client.dateOfBirth, "^\\d{2}\\.\\d{2}\\.\\d{4}$")) {
            errors.add(DATE_OF_BIRTH_ERROR_KEY)
        }

        if (!validateField(client.passportNumber, "^\\d{10}$")) {
            errors.add(PASSPORT_NUMBER_ERROR_KEY)
        }

        if (!validateField(client.passportExpirationDate, "^\\d{2}\\.\\d{2}\\.\\d{4}$")) {
            errors.add(PASSPORT_EXPIRATION_DATE_ERROR_KEY)
        }

        return errors
    }

    private fun generateRandomTicket(): Int {
        val seed = System.currentTimeMillis()
        val random = Random(seed)
        return random.nextInt(100000, 999999)
    }

    override fun postClients(clients: List<ClientModel>): String {
        return generateRandomTicket().toString()
    }
}