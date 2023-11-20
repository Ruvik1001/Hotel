package com.example.hotel.presentation.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.HotelModel
import com.example.domain.usecase.GetHotelsFromApiUseCase
import com.example.hotel.special.adapter.image.ImageFragmentAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for the HotelMainActivity.
 *
 * @param getHotelsFromApiUseCase Use case for fetching hotel data from the API.
 */
class HotelMainViewModel(
    private val getHotelsFromApiUseCase: GetHotelsFromApiUseCase
) : ViewModel() {
    private val hotelsMutableLiveData = MutableLiveData<HotelModel>()
    val hotelLiveData: LiveData<HotelModel> = hotelsMutableLiveData
    private val adapterMutableLiveData = MutableLiveData<ImageFragmentAdapter>()
    val adapterLiveData: LiveData<ImageFragmentAdapter> = adapterMutableLiveData

    /**
     * Initialization block to fetch hotel data from the API.
     */
    init {
        CoroutineScope(Dispatchers.IO).launch {
            val hotel = getHotelsFromApiUseCase.execute()
            when (hotel.isSuccess) {
                true -> withContext(Dispatchers.Main) {
                    hotelsMutableLiveData.postValue(hotel.getOrThrow())
                }
                else -> return@launch
            }
        }
    }
}
