package com.example.domain.repository

import com.example.domain.model.HotelModel

/**
 * Repository interface for hotel-related operations.
 */
interface HotelRepository {
    /**
     * Retrieves a list of hotels from an external API.
     *
     * @return A [Result] containing the [HotelModel] or an [Exception] in case of failure.
     */
    suspend fun getHotelsFromApi(): Result<HotelModel>
}
