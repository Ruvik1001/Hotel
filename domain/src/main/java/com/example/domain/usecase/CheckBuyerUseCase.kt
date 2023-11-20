package com.example.domain.usecase

import com.example.domain.model.BuyerModel
import com.example.domain.repository.BuyerRepository

/**
 * Use case class for validating buyer information.
 *
 * @param buyerRepository The repository providing validation logic for buyer information.
 */
class CheckBuyerUseCase(private val buyerRepository: BuyerRepository) {

    /**
     * Executes the use case to validate buyer information.
     *
     * @param buyerModel The [BuyerModel] containing buyer information to be validated.
     * @return A list of validation results indicating the status of various buyer information fields.
     */
    fun execute(buyerModel: BuyerModel): List<String> {
        return buyerRepository.validate(buyerModel)
    }
}
