package com.example.domain.repository

import com.example.domain.model.RoomInfoModel

/**
 * Repository interface for room information-related operations.
 */
interface RoomInfoRepository {
    /**
     * Retrieves room information from an external API.
     *
     * @return A [Result] containing the [RoomInfoModel] or an [Exception] in case of failure.
     */
    suspend fun getRoomInfoFromApi(): Result<RoomInfoModel>
}
