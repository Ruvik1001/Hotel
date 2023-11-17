package com.example.data.repository

import com.example.data.api.ApiService
import com.example.domain.model.RoomInfoModel
import com.example.domain.repository.RoomInfoRepository

class RoomInfoRepository(private val apiService: ApiService, private val apiUrl: String): RoomInfoRepository {
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