package com.example.hotel.presentation.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.RoomInfoModel
import com.example.domain.usecase.GetRoomInfoFromApiUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomReservationViewModel(
    private val getRoomInfoFromApiUseCase: GetRoomInfoFromApiUseCase
): ViewModel() {
    private val roomsMutableLiveData = MutableLiveData<RoomInfoModel>()
    val roomLiveData: LiveData<RoomInfoModel> = roomsMutableLiveData

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val room = getRoomInfoFromApiUseCase.execute()
            when (room.isSuccess) {
                room.isSuccess -> withContext(Dispatchers.Main) {
                    roomsMutableLiveData.postValue(room.getOrThrow())
                }
                else -> return@launch
            }
        }
    }
}