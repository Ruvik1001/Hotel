package com.example.hotel.di

import com.example.hotel.presentation.hotel.HotelMainViewModel
import com.example.hotel.presentation.reservation.RoomReservationViewModel
import com.example.hotel.presentation.room.RoomChangeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<HotelMainViewModel> {
        HotelMainViewModel(
            getHotelsFromApiUseCase = get()
        )
    }

    viewModel<RoomChangeViewModel> {
        RoomChangeViewModel(
            getRoomsFromApiUseCase = get()
        )
    }

    viewModel<RoomReservationViewModel> {
        RoomReservationViewModel(
            context = androidContext(),
            getRoomInfoFromApiUseCase = get(),
            checkBuyerUseCase = get(),
            checkClientsUseCase = get()
        )
    }
}
