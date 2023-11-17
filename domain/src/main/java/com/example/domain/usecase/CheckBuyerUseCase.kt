package com.example.domain.usecase

import com.example.domain.model.BuyerModel
import com.example.domain.repository.BuyerRepository

class CheckBuyerUseCase(private val buyerRepository: BuyerRepository) {
    fun execute(buyerModel: BuyerModel): List<String> {
        return buyerRepository.validate(buyerModel)
    }
}