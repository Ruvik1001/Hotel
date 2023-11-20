package com.example.data.repository

import com.example.domain.model.ClientModel
import com.example.domain.repository.ClientRepository
import kotlin.random.Random

// Constants for error and success keys
const val FIRST_NAME_ERROR_KEY = "FIRST_NAME_ERROR"
const val LAST_NAME_ERROR_KEY = "LAST_NAME_ERROR"
const val DATE_OF_BIRTH_ERROR_KEY = "DATE_OF_BIRTH_ERROR"
const val CITIZENSHIP_ERROR_KEY = "CITIZENSHIP_ERROR"
const val PASSPORT_NUMBER_ERROR_KEY = "PASSPORT_NUMBER_ERROR"
const val PASSPORT_EXPIRATION_DATE_ERROR_KEY = "PASSPORT_EXPIRATION_DATE_ERROR"
const val FIRST_NAME_OK_KEY = "FIRST_NAME_OK"
const val LAST_NAME_OK_KEY = "LAST_NAME_OK"
const val DATE_OF_BIRTH_OK_KEY = "DATE_OF_BIRTH_OK"
const val CITIZENSHIP_OK_KEY = "CITIZENSHIP_OK"
const val PASSPORT_NUMBER_OK_KEY = "PASSPORT_NUMBER_OK"
const val PASSPORT_EXPIRATION_DATE_OK_KEY = "PASSPORT_EXPIRATION_DATE_OK"

/**
 * Repository class for validating and handling client-related operations.
 */
class ClientRepository : ClientRepository {

    /**
     * Validates a given field against a specified pattern.
     *
     * @param value The value of the field to be validated.
     * @param pattern The regex pattern for validation.
     * @return `true` if the field is valid, otherwise `false`.
     */
    private fun validateField(value: String, pattern: String): Boolean {
        return Regex(pattern).matches(value) && value.isNotBlank()
    }

    /**
     * Validates the fields of a ClientModel.
     *
     * @param client The ClientModel to be validated.
     * @return A list of string keys indicating the validation result.
     */
    override fun validateClient(client: ClientModel): List<String> {
        val errors = mutableListOf<String>()

        // Validate first name
        if (!validateField(client.firstName, "^[A-Za-zА-Яа-я\\s'-]+$")) {
            errors.add(FIRST_NAME_ERROR_KEY)
        } else {
            errors.add(FIRST_NAME_OK_KEY)
        }

        // Validate last name
        if (!validateField(client.lastName, "^[A-Za-zА-Яа-я\\s'-]+$")) {
            errors.add(LAST_NAME_ERROR_KEY)
        } else {
            errors.add(LAST_NAME_OK_KEY)
        }

        // Validate date of birth
        if (!validateField(client.dateOfBirth, "^\\d{2}\\d{2}\\d{4}$")) {
            errors.add(DATE_OF_BIRTH_ERROR_KEY)
        } else {
            errors.add(DATE_OF_BIRTH_OK_KEY)
        }

        // Validate citizenship
        if (!validateField(client.citizenship, "^[A-Za-zА-Яа-я]+$")) {
            errors.add(CITIZENSHIP_ERROR_KEY)
        } else {
            errors.add(CITIZENSHIP_OK_KEY)
        }

        // Validate passport number
        if (!validateField(client.passportNumber, "^\\d{7}$")) {
            errors.add(PASSPORT_NUMBER_ERROR_KEY)
        } else {
            errors.add(PASSPORT_NUMBER_OK_KEY)
        }

        // Validate passport expiration date
        if (!validateField(client.passportExpirationDate, "^\\d{2}\\d{2}\\d{4}$")) {
            errors.add(PASSPORT_EXPIRATION_DATE_ERROR_KEY)
        } else {
            errors.add(PASSPORT_EXPIRATION_DATE_OK_KEY)
        }

        return errors
    }

    /**
     * Generates a random ticket number.
     *
     * @return A randomly generated ticket number.
     */
    fun generateRandomTicket(): Int {
        val seed = System.currentTimeMillis()
        val random = Random(seed)
        return random.nextInt(100000, 999999)
    }

    /**
     * Posts a list of clients and returns a generated ticket number as a string.
     *
     * @param clients The list of clients to be posted.
     * @return A generated ticket number as a string.
     */
    override fun postClients(clients: List<ClientModel>): String {
        return generateRandomTicket().toString()
    }
}
