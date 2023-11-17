package com.example.data.repository

import com.example.data.api.ApiService
import com.example.domain.model.RoomModel
import com.example.domain.model.RoomsListModel
import com.example.domain.repository.RoomRepository

class RoomRepository(private val apiService: ApiService, private val apiUrl: String): RoomRepository {
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