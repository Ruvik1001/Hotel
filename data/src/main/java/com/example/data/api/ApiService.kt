package com.example.data.api

import com.example.domain.model.HotelModel
import com.example.domain.model.RoomInfoModel
import com.example.domain.model.RoomModel
import com.example.domain.model.RoomsListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getHotels(@Url url: String): Response<HotelModel>

    @GET
    suspend fun getRooms(@Url url: String): Response<RoomsListModel>

    @GET
    suspend fun getRoomInfo(@Url url: String): Response<RoomInfoModel>
}