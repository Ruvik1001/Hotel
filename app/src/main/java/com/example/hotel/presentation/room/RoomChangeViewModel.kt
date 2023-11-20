package com.example.hotel.presentation.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.RoomsListModel
import com.example.domain.usecase.GetRoomsFromApiUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for handling room change logic.
 */
class RoomChangeViewModel(
    private val getRoomsFromApiUseCase: GetRoomsFromApiUseCase
): ViewModel(){
    private val roomsMutableLiveData = MutableLiveData<RoomsListModel>()
    val roomsLiveData: LiveData<RoomsListModel> = roomsMutableLiveData

    /**
     * Initialization block that launches a coroutine to fetch rooms from the API.
     * The result is posted to the LiveData for observation.
     */
    init {
        CoroutineScope(Dispatchers.IO).launch {
            val rooms = getRoomsFromApiUseCase.execute()
            when (rooms.isSuccess) {
                rooms.isSuccess -> withContext(Dispatchers.Main) {
                    roomsMutableLiveData.postValue(rooms.getOrThrow())
                }
                else -> return@launch
            }
        }
    }
}