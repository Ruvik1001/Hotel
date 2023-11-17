package com.example.domain.repository

import com.example.domain.model.RoomInfoModel

interface RoomInfoRepository {
    suspend fun getRoomInfoFromApi(): Result<RoomInfoModel>
}