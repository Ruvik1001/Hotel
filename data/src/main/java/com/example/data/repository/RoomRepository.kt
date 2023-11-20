package com.example.data.repository

import com.example.data.api.ApiService
import com.example.domain.model.RoomsListModel
import com.example.domain.repository.RoomRepository

/**
 * Repository class for handling room-related operations by making API requests.
 *
 * @param apiService The [ApiService] instance for making API requests.
 * @param apiUrl The base URL for the room API.
 */
class RoomRepository(private val apiService: ApiService, private val apiUrl: String): RoomRepository {

    /**
     * Fetches rooms from the API.
     *
     * @return A [Result] containing either the [RoomsListModel] or an exception if the request fails.
     */
    override suspend fun getRoomsFromApi(): Result<RoomsListModel> {
        return try {
            val response = apiService.getRooms(apiUrl)

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
