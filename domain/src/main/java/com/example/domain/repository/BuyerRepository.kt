package com.example.domain.repository

import com.example.domain.model.BuyerModel

interface BuyerRepository {
    /*
        return List< ERROR_STRING_KEY >
     */
    fun validate(buyerModel: BuyerModel): List<String>
}