package com.example.data.repository

import com.example.data.api.ApiService
import com.example.domain.model.HotelModel
import com.example.domain.repository.HotelRepository

/**
 * Repository class for handling hotel-related operations by making API requests.
 *
 * @param apiService The [ApiService] instance for making API requests.
 * @param apiUrl The base URL for the hotel API.
 */
class HotelRepository(private val apiService: ApiService, private val apiUrl: String): HotelRepository {

    /**
     * Fetches a list of hotels from the API.
     *
     * @return A [Result] containing either the [HotelModel] or an exception if the request fails.
     */
    override suspend fun getHotelsFromApi(): Result<HotelModel> {
        return try {
            val response = apiService.getHotels(apiUrl)

            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("API request failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
