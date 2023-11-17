package com.example.domain.usecase

import com.example.domain.model.HotelModel
import com.example.domain.repository.HotelRepository

class GetHotelsFromApiUseCase(private val hotelRepository: HotelRepository) {
    suspend fun execute(): Result<HotelModel> {
        return hotelRepository.getHotelsFromApi()
    }
}