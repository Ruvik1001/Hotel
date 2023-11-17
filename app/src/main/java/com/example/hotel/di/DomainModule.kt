package com.example.hotel.di

import com.example.data.repository.BuyerRepository
import com.example.data.repository.ClientRepository
import com.example.data.repository.HotelRepository
import com.example.data.repository.RoomInfoRepository
import com.example.data.repository.RoomRepository
import com.example.domain.usecase.CheckBuyerUseCase
import com.example.domain.usecase.CheckClientsUseCase
import com.example.domain.usecase.GetHotelsFromApiUseCase
import com.example.domain.usecase.GetRoomInfoFromApiUseCase
import com.example.domain.usecase.GetRoomsFromApiUseCase
import com.example.domain.usecase.PostClientsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<CheckBuyerUseCase> {
        CheckBuyerUseCase(get<BuyerRepository>())
    }

    factory<CheckClientsUseCase> {
        CheckClientsUseCase(get<ClientRepository>())
    }

    factory<PostClientsUseCase> {
        PostClientsUseCase(get<ClientRepository>())
    }

    factory<GetHotelsFromApiUseCase> {
        GetHotelsFromApiUseCase(get<HotelRepository>())
    }

    factory<GetRoomInfoFromApiUseCase> {
        GetRoomInfoFromApiUseCase(get<RoomInfoRepository>())
    }

    factory<GetRoomsFromApiUseCase> {
        GetRoomsFromApiUseCase(get<RoomRepository>())
    }
}