package com.example.domain.usecase

import com.example.domain.model.RoomInfoModel
import com.example.domain.repository.RoomInfoRepository

/**
 * Use case class for retrieving room information from the API.
 *
 * @param roomInfoRepository The repository providing access to room information.
 */
class GetRoomInfoFromApiUseCase(private val roomInfoRepository: RoomInfoRepository) {

    /**
     * Executes the use case to retrieve room information from the API.
     *
     * @return A [Result] containing either the [RoomInfoModel] on success or an exception on failure.
     */
    suspend fun execute(): Result<RoomInfoModel> {
        return roomInfoRepository.getRoomInfoFromApi()
    }
}
