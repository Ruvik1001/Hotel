package com.example.hotel.di

import com.example.data.api.ApiService
import com.example.data.repository.BuyerRepository
import com.example.data.repository.ClientRepository
import com.example.data.repository.HotelRepository
import com.example.data.repository.RoomInfoRepository
import com.example.data.repository.RoomRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    single<BuyerRepository> {
        BuyerRepository()
    }

    single<ClientRepository> {
        ClientRepository()
    }

    single<HotelRepository> {
        HotelRepository(
            apiService = get(),
            apiUrl = "/v3/d144777c-a67f-4e35-867a-cacc3b827473"
        )
    }

    single<RoomInfoRepository> {
        RoomInfoRepository(
            apiService = get(),
            apiUrl = "/v3/63866c74-d593-432c-af8e-f279d1a8d2ff"
        )
    }

    single<RoomRepository> {
        RoomRepository(
            apiService = get(),
            apiUrl = "/v3/8b532701-709e-4194-a41c-1a903af00195"
        )
    }
}