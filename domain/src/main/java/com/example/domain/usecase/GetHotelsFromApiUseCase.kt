package com.example.domain.usecase

import com.example.domain.model.HotelModel
import com.example.domain.repository.HotelRepository

/**
 * Use case class for retrieving hotel information from the API.
 *
 * @param hotelRepository The repository providing access to hotel information.
 */
class GetHotelsFromApiUseCase(private val hotelRepository: HotelRepository) {

    /**
     * Executes the use case to retrieve hotel information from the API.
     *
     * @return A [Result] containing either the [HotelModel] on success or an exception on failure.
     */
    suspend fun execute(): Result<HotelModel> {
        return hotelRepository.getHotelsFromApi()
    }
}
