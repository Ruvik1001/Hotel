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
import com.example.hotel.special.interfaces.OnEditedCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for handling room reservation.
 */
class RoomReservationViewModel(
    private val context: Context,
    private val getRoomInfoFromApiUseCase: GetRoomInfoFromApiUseCase,
    private val checkBuyerUseCase: CheckBuyerUseCase,
    private val checkClientsUseCase: CheckClientsUseCase
): ViewModel(), OnEditedCallback {

    // LiveData for observing room information
    private val roomsMutableLiveData = MutableLiveData<RoomInfoModel>()
    val roomLiveData: LiveData<RoomInfoModel> = roomsMutableLiveData

    // LiveData for observing reservation cost
    private val costMutableLiveData = MutableLiveData<Int>()
    val costLiveData: LiveData<Int> = costMutableLiveData

    /**
     * Initialization block that launches a coroutine to fetch room information from the API.
     * The result is posted to the LiveData for observation.
     */
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

    // Properties for storing various reservation models
    private var hotelModel: ReservationHotelModel? = null
    private var  tourModel: ReservationTourModel? = null
    private var buyerModel: BuyerModel? = null
    private var clientModel: ClientModel? = null
    private var clientAddModel: ClientAddModel? = null
    private var costModel: ReservationCostModel? = null

    /**
     * Get the reservation hotel model, creating it if not already initialized.
     */
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

    /**
     * Get the reservation tour model, creating it if not already initialized.
     */
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

    /**
     * Get the reservation buyer model, creating it if not already initialized.
     */
    fun getReservationBuyerModel(): BuyerModel {
        if (buyerModel == null) buyerModel = BuyerModel()
        return buyerModel!!
    }

    /**
     * Get the reservation client model, creating it if not already initialized.
     */
    fun getReservationClientModel(): ClientModel {
        if (clientModel == null) clientModel = ClientModel()
        return clientModel!!
    }

    /**
     * Get the reservation additional client model, creating it if not already initialized.
     */
    fun getReservationAddClientModel(): ClientAddModel {
        if (clientAddModel == null) clientAddModel = ClientAddModel(
            "Добавить туриста"
        )
        return clientAddModel!!
    }

    /**
     * Get the reservation cost model, creating it if not already initialized.
     */
    fun getReservationCostModel(): ReservationCostModel {
        if (costModel == null) costModel = ReservationCostModel(
            roomsMutableLiveData.value!!.tour_price,
            roomsMutableLiveData.value!!.fuel_charge,
            roomsMutableLiveData.value!!.service_charge
        )
        return costModel!!
    }

    /**
     * Validate the buyer model and return a list of validation results.
     *
     * @param buyerModel The buyer model to be validated.
     * @return A list of validation results.
     */
    fun validateBuyer(buyerModel: BuyerModel): List<String> {
        return checkBuyerUseCase.execute(buyerModel)
    }

    /**
     * Validate the client model and return a list of validation results.
     *
     * @param clientModel The client model to be validated.
     * @return A list of validation results.
     */
    fun validateClient(clientModel: ClientModel): List<String> {
        return checkClientsUseCase.execute(clientModel)
    }

    /**
     * Update the background tint of the phone view based on validation result.
     *
     * @param buyerModel The buyer model used for validation.
     * @param viewPhone The TableLayout representing the phone view.
     */
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

    /**
     * Update the background tint of the mail view based on validation result.
     *
     * @param buyerModel The buyer model to be validated.
     * @param viewMail The TableLayout representing the mail view.
     */
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

    /**
     * Update the background tint of the first name view based on validation result.
     *
     * @param clientModel The client model to be validated.
     * @param viewMail The TableLayout representing the first name view.
     */
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

    /**
     * Update the background tint of the last name view based on validation result.
     *
     * @param clientModel The client model to be validated.
     * @param viewMail The TableLayout representing the last name view.
     */
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

    /**
     * Update the background tint of the date of birth view based on validation result.
     *
     * @param clientModel The client model to be validated.
     * @param viewMail The TableLayout representing the date of birth view.
     */
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

    /**
     * Update the background tint of the citizenship view based on validation result.
     *
     * @param clientModel The client model to be validated.
     * @param viewMail The TableLayout representing the citizenship view.
     */
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

    /**
     * Update the background tint of the passport number view based on validation result.
     *
     * @param clientModel The client model to be validated.
     * @param viewMail The TableLayout representing the passport number view.
     */
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

    /**
     * Update the background tint of the passport expiration date view based on validation result.
     *
     * @param clientModel The client model to be validated.
     * @param viewMail The TableLayout representing the passport expiration date view.
     */
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

    /**
     * Update the reservation cost.
     *
     * @param cost The updated cost value.
     */
    override fun updatePrice(cost: Int) {
        costMutableLiveData.postValue(cost)
    }

}
