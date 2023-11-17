package com.example.data.repository

import com.example.data.api.ApiService
import com.example.domain.model.HotelModel
import com.example.domain.repository.HotelRepository

class HotelRepository(private val apiService: ApiService, private val apiUrl: String): HotelRepository {
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