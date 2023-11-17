package com.example.domain.repository

import com.example.domain.model.HotelModel

interface HotelRepository {
    suspend fun getHotelsFromApi(): Result<HotelModel>
}