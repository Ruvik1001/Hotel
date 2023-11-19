package com.example.hotel.presentation.reservation

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.widget.TableLayout
import androidx.core.view.ViewCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.repository.CITIZENSHIP_ERROR_KEY
import com.example.data.repository.CITIZENSHIP_OK_KEY
import com.example.data.repository.DATE_OF_BIRTH_ERROR_KEY
import com.example.data.repository.DATE_OF_BIRTH_OK_KEY
import com.example.data.repository.FIRST_NAME_ERROR_KEY
import com.example.data.repository.FIRST_NAME_OK_KEY
import com.example.data.repository.LAST_NAME_ERROR_KEY
import com.example.data.repository.LAST_NAME_OK_KEY
import com.example.data.repository.MAIL_ERROR_KEY
import com.example.data.repository.MAIL_OK_KEY
import com.example.data.repository.PASSPORT_EXPIRATION_DATE_ERROR_KEY
import com.example.data.repository.PASSPORT_EXPIRATION_DATE_OK_KEY
import com.example.data.repository.PASSPORT_NUMBER_ERROR_KEY
import com.example.data.repository.PASSPORT_NUMBER_OK_KEY
import com.example.data.repository.PHONE_ERROR_KEY
import com.example.data.repository.PHONE_OK_KEY
import com.example.domain.model.BuyerModel
import com.example.domain.model.ClientAddModel
import com.example.domain.model.ClientModel
import com.example.domain.model.ReservationCostModel
import com.example.domain.model.ReservationHotelModel
import com.example.domain.model.ReservationTourModel
import com.example.domain.model.RoomInfoModel
import com.example.domain.usecase.CheckBuyerUseCase
import com.example.domain.usecase.CheckClientsUseCase
import com.example.domain.usecase.GetRoomInfoFromApiUseCase
import com.example.hotel.R
import com.example.hotel.special.interfaces.OnEdited
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomReservationViewModel(
    private val context: Context,
    private val getRoomInfoFromApiUseCase: GetRoomInfoFromApiUseCase,
    private val checkBuyerUseCase: CheckBuyerUseCase,
    private val checkClientsUseCase: CheckClientsUseCase
): ViewModel(), OnEdited {
    private val roomsMutableLiveData = MutableLiveData<RoomInfoModel>()
    val roomLiveData: LiveData<RoomInfoModel> = roomsMutableLiveData

    private val costMutableLiveData = MutableLiveData<Int>()
    val costLiveData: LiveData<Int> = costMutableLiveData

    init {
        CoroutineScope(Dispatchers.IO).launch {
            Log.e("HOTEL", "VM INIT")
            val room = getRoomInfoFromApiUseCase.execute()
            when (room.isSuccess) {
                room.isSuccess -> withContext(Dispatchers.Main) {
                    roomsMutableLiveData.postValue(room.getOrThrow())
                }
                else -> return@launch
            }
        }
    }

    private var hotelModel: ReservationHotelModel? = null
    fun getReservationHotelModel(): ReservationHotelModel {
        if (hotelModel == null)
            hotelModel = ReservationHotelModel(
                roomsMutableLiveData.value!!.hotel_name,
                roomsMutableLiveData.value!!.hotel_adress,
                roomsMutableLiveData.value!!.horating,
                roomsMutableLiveData.value!!.rating_name
            )
        return hotelModel!!
    }

    private var  tourModel: ReservationTourModel? = null
    fun getReservationTourModel(): ReservationTourModel {
        if (tourModel == null) tourModel = ReservationTourModel(
            roomsMutableLiveData.value!!.hotel_name,
            roomsMutableLiveData.value!!.departure,
            roomsMutableLiveData.value!!.arrival_country,
            roomsMutableLiveData.value!!.tour_date_start,
            roomsMutableLiveData.value!!.tour_date_stop,
            roomsMutableLiveData.value!!.number_of_nights,
            roomsMutableLiveData.value!!.room,
            roomsMutableLiveData.value!!.nutrition,
        )
        return tourModel!!
    }

    private var buyerModel: BuyerModel? = null
    fun getReservationBuyerModel(): BuyerModel {
        if (buyerModel == null) buyerModel = BuyerModel()
        return buyerModel!!
    }

    private var clientModel: ClientModel? = null
    fun getReservationClientModel(): ClientModel {
        if (clientModel == null) clientModel = ClientModel()
        return clientModel!!
    }

    private var clientAddModel: ClientAddModel? = null
    fun getReservationAddClientModel(): ClientAddModel {
        if (clientAddModel == null) clientAddModel = ClientAddModel(
            "Добавить туриста"
        )
        return clientAddModel!!
    }

    private var costModel: ReservationCostModel? = null
    fun getReservationCostModel(): ReservationCostModel {
        if (costModel == null) costModel = ReservationCostModel(
            roomsMutableLiveData.value!!.tour_price,
            roomsMutableLiveData.value!!.fuel_charge,
            roomsMutableLiveData.value!!.service_charge
        )
        return costModel!!
    }

    fun validateBuyer(buyerModel: BuyerModel): List<String> {
        return checkBuyerUseCase.execute(buyerModel)
    }

    fun validateClient(clientModel: ClientModel): List<String> {
        return checkClientsUseCase.execute(clientModel)
    }

    override fun updatePhone(
        buyerModel: BuyerModel,
        viewPhone: TableLayout
    ) {
        val result = validateBuyer(buyerModel)
        for (elem in result) {
            when (elem) {
                PHONE_ERROR_KEY -> ViewCompat.setBackgroundTintList(viewPhone,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.error)))
                PHONE_OK_KEY -> ViewCompat.setBackgroundTintList(viewPhone,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.background_color)))
            }
        }
    }

    override fun updateMail(
        buyerModel: BuyerModel,
        viewMail: TableLayout
    ) {
        val result = validateBuyer(buyerModel)
        for (elem in result) {
            when (elem) {
                MAIL_ERROR_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.error)))
                MAIL_OK_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.background_color)))
            }
        }
    }

    override fun updateName(
        clientModel: ClientModel,
        viewMail: TableLayout
    ) {
        val result = validateClient(clientModel)
        for (elem in result) {
            when (elem) {
                FIRST_NAME_ERROR_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.error)))
                FIRST_NAME_OK_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.background_color)))
            }
        }
    }

    override fun updateLastName(
        clientModel: ClientModel,
        viewMail: TableLayout
    ) {
        val result = validateClient(clientModel)
        for (elem in result) {
            when (elem) {
                LAST_NAME_ERROR_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.error)))
                LAST_NAME_OK_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.background_color)))
            }
        }
    }

    override fun updateDateOfBirth(
        clientModel: ClientModel,
        viewMail: TableLayout
    ) {
        val result = validateClient(clientModel)
        for (elem in result) {
            when (elem) {
                DATE_OF_BIRTH_ERROR_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.error)))
                DATE_OF_BIRTH_OK_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.background_color)))
            }
        }
    }

    override fun updateCitizenship(
        clientModel: ClientModel,
        viewMail: TableLayout
    ) {
        val result = validateClient(clientModel)
        for (elem in result) {
            when (elem) {
                CITIZENSHIP_ERROR_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.error)))
                CITIZENSHIP_OK_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.background_color)))
            }
        }
    }

    override fun updatePassportNumber(
        clientModel: ClientModel,
        viewMail: TableLayout
    ) {
        val result = validateClient(clientModel)
        for (elem in result) {
            when (elem) {
                PASSPORT_NUMBER_ERROR_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.error)))
                PASSPORT_NUMBER_OK_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.background_color)))
            }
        }
    }

    override fun updatePassportExpirationDate(
        clientModel: ClientModel,
        viewMail: TableLayout
    ) {
        val result = validateClient(clientModel)
        for (elem in result) {
            when (elem) {
                PASSPORT_EXPIRATION_DATE_ERROR_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.error)))
                PASSPORT_EXPIRATION_DATE_OK_KEY -> ViewCompat.setBackgroundTintList(viewMail,
                    ColorStateList.valueOf(context.resources.getColor(
                        R.color.background_color)))
            }
        }
    }

    override fun updatePrice(cost: Int) {
        costMutableLiveData.postValue(cost)
    }
}