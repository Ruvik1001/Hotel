package com.example.data.api

import com.example.domain.model.HotelModel
import com.example.domain.model.RoomInfoModel
import com.example.domain.model.RoomModel
import com.example.domain.model.RoomsListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    /**
     * Retrieves hotel information.
     *
     * @param url The URL for the hotel information.
     * @return A [Response] containing a [HotelModel].
     */
    @GET
    suspend fun getHotels(@Url url: String): Response<HotelModel>

    /**
     * Retrieves a list of rooms.
     *
     * @param url The URL for the room list.
     * @return A [Response] containing a [RoomsListModel].
     */
    @GET
    suspend fun getRooms(@Url url: String): Response<RoomsListModel>

    /**
     * Retrieves detailed information about a specific room.
     *
     * @param url The URL for the room information.
     * @return A [Response] containing a [RoomInfoModel].
     */
    @GET
    suspend fun getRoomInfo(@Url url: String): Response<RoomInfoModel>
}
