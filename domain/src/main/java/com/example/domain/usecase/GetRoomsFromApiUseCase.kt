package com.example.domain.usecase

import com.example.domain.model.RoomModel
import com.example.domain.model.RoomsListModel
import com.example.domain.repository.RoomRepository

class GetRoomsFromApiUseCase(private val roomRepository: RoomRepository) {
    suspend fun execute(): Result<RoomsListModel> {
        return roomRepository.getRoomsFromApi()
    }
}