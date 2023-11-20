package com.example.data.repository

import com.example.data.api.ApiService
import com.example.domain.model.RoomInfoModel
import com.example.domain.repository.RoomInfoRepository

/**
 * Repository class for handling room information-related operations by making API requests.
 *
 * @param apiService The [ApiService] instance for making API requests.
 * @param apiUrl The base URL for the room information API.
 */
class RoomInfoRepository(private val apiService: ApiService, private val apiUrl: String): RoomInfoRepository {

    /**
     * Fetches room information from the API.
     *
     * @return A [Result] containing either the [RoomInfoModel] or an exception if the request fails.
     */
    override suspend fun getRoomInfoFromApi(): Result<RoomInfoModel> {
        return try {
            val response = apiService.getRoomInfo(apiUrl)

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
