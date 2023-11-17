package com.example.domain.usecase

import com.example.domain.model.RoomInfoModel
import com.example.domain.repository.RoomInfoRepository

class GetRoomInfoFromApiUseCase(private val roomInfoRepository: RoomInfoRepository) {
    suspend fun execute(): Result<RoomInfoModel> {
        return roomInfoRepository.getRoomInfoFromApi()
    }
}