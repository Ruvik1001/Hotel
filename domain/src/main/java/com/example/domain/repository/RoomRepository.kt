package com.example.domain.repository

import com.example.domain.model.RoomModel
import com.example.domain.model.RoomsListModel

interface RoomRepository {
    suspend fun getRoomsFromApi(): Result<RoomsListModel>
}