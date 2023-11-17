package com.example.hotel.presentation.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.HotelModel
import com.example.domain.usecase.GetHotelsFromApiUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HotelMainViewModel(
    private val getHotelsFromApiUseCase: GetHotelsFromApiUseCase
): ViewModel() {
    private val hotelsMutableLiveData = MutableLiveData<HotelModel>()
    val hotelLiveData: LiveData<HotelModel> = hotelsMutableLiveData

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val hotel = getHotelsFromApiUseCase.execute()
            when (hotel.isSuccess) {
                hotel.isSuccess -> withContext(Dispatchers.Main) {
                    hotelsMutableLiveData.postValue(hotel.getOrThrow())
                }
                else -> return@launch
            }
        }
    }


}