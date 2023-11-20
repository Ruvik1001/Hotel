package com.example.domain.repository

import com.example.domain.model.RoomsListModel

/**
 * Repository interface for room-related operations.
 */
interface RoomRepository {
    /**
     * Retrieves rooms information from an external API.
     *
     * @return A [Result] containing the [RoomsListModel] or an [Exception] in case of failure.
     */
    suspend fun getRoomsFromApi(): Result<RoomsListModel>
}
