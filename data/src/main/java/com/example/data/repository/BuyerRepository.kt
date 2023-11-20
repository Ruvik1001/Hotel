package com.example.data.repository

import com.example.domain.model.BuyerModel
import com.example.domain.repository.BuyerRepository

// Constants for error and success keys
const val PHONE_ERROR_KEY = "PHONE_ERROR"
const val MAIL_ERROR_KEY = "MAIL_ERROR"
const val PHONE_OK_KEY = "PHONE_OK"
const val MAIL_OK_KEY = "MAIL_OK"

/**
 * Repository class for validating phone numbers and email addresses.
 */
class BuyerRepository : BuyerRepository {

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
     * Validates the phone number and email address fields of a BuyerModel.
     *
     * @param buyerModel The BuyerModel to be validated.
     * @return A list of string keys indicating the validation result.
     */
    override fun validate(buyerModel: BuyerModel): List<String> {
        val listOfBadInput = mutableListOf<String>()

        // Validate phone number
        if (!validateField(
                buyerModel.phoneNumber
                    .replace("+", "")
                    .replace("(", "")
                    .replace(")", "")
                    .replace("-", ""),
                "^7\\d{10}$"
            )
        ) {
            listOfBadInput.add(PHONE_ERROR_KEY)
        } else {
            listOfBadInput.add(PHONE_OK_KEY)
        }

        // Validate email address
        if (!validateField(
                buyerModel.mailAddress,
                "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"
            )
        ) {
            listOfBadInput.add(MAIL_ERROR_KEY)
        } else {
            listOfBadInput.add(MAIL_OK_KEY)
        }

        return listOfBadInput
    }
}
