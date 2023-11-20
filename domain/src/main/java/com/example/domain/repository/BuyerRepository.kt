package com.example.domain.repository

import com.example.domain.model.BuyerModel

/**
 * Repository interface for validating buyer information.
 */
interface BuyerRepository {
    /**
     * Validates the provided [BuyerModel].
     *
     * @param buyerModel The [BuyerModel] to be validated.
     * @return A [List] of [String] representing validation results or error keys.
     */
    fun validate(buyerModel: BuyerModel): List<String>
}
