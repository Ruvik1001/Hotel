package com.example.domain.usecase

import com.example.domain.model.RoomsListModel
import com.example.domain.repository.RoomRepository

/**
 * Use case class for retrieving rooms from the API.
 *
 * @param roomRepository The repository providing access to rooms.
 */
class GetRoomsFromApiUseCase(private val roomRepository: RoomRepository) {

    /**
     * Executes the use case to retrieve rooms from the API.
     *
     * @return A [Result] containing either the [RoomsListModel] on success or an exception on failure.
     */
    suspend fun execute(): Result<RoomsListModel> {
        return roomRepository.getRoomsFromApi()
    }
}
