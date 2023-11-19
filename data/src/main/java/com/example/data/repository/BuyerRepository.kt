package com.example.data.repository

import com.example.domain.model.BuyerModel
import com.example.domain.repository.BuyerRepository

const val PHONE_ERROR_KEY = "PHONE_ERROR"
const val MAIL_ERROR_KEY = "MAIL_ERROR"
const val PHONE_OK_KEY = "PHONE_OK"
const val MAIL_OK_KEY = "MAIL_OK"

class BuyerRepository: BuyerRepository {
    private fun validateField(value: String, pattern: String): Boolean {
        return Regex(pattern).matches(value) && value.isNotBlank()
    }

    override fun validate(buyerModel: BuyerModel): List<String> {
        val listOfBadInput = mutableListOf<String>()

        if (!validateField(buyerModel.phoneNumber
                .replace("+","")
                .replace("(","")
                .replace(")","")
                .replace("-","")
                , "^7\\d{10}$")) {
            listOfBadInput.add(PHONE_ERROR_KEY)
        }
        else listOfBadInput.add(PHONE_OK_KEY)

        if (!validateField(buyerModel.mailAddress, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")) {
            listOfBadInput.add(MAIL_ERROR_KEY)
        }
        else listOfBadInput.add(MAIL_OK_KEY)

        return listOfBadInput
    }
}