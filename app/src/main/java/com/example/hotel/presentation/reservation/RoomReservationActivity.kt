package com.example.hotel.presentation.reservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.repository.CITIZENSHIP_ERROR_KEY
import com.example.data.repository.DATE_OF_BIRTH_ERROR_KEY
import com.example.data.repository.FIRST_NAME_ERROR_KEY
import com.example.data.repository.LAST_NAME_ERROR_KEY
import com.example.data.repository.MAIL_ERROR_KEY
import com.example.data.repository.PASSPORT_EXPIRATION_DATE_ERROR_KEY
import com.example.data.repository.PASSPORT_NUMBER_ERROR_KEY
import com.example.data.repository.PHONE_ERROR_KEY
import com.example.domain.model.BuyerModel
import com.example.domain.model.ClientAddModel
import com.example.domain.model.ClientModel
import com.example.domain.model.ReservationCostModel
import com.example.domain.model.ReservationHotelModel
import com.example.domain.model.ReservationTourModel
import com.example.hotel.R
import com.example.hotel.databinding.ActivityRoomReservatiomBinding
import com.example.hotel.presentation.succes.SuccesActivity
import com.example.hotel.special.adapter.reservation.ReservationMultiAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomReservationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomReservatiomBinding
    private val viewModel by viewModel<RoomReservationViewModel>()
    private lateinit var a: ReservationMultiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomReservatiomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        viewModel.roomLiveData.observe(this) {
            a = ReservationMultiAdapter(
                this,
                try {
                    Log.e("HOTEL", "adapter TRY")
                    mutableListOf(
                        viewModel.getReservationHotelModel(),
                        viewModel.getReservationTourModel(),
                        viewModel.getReservationBuyerModel(),
                        viewModel.getReservationClientModel(),
                        viewModel.getReservationAddClientModel(),
                        viewModel.getReservationCostModel()
                    )
                } catch (e : Exception) {
                    Log.e("HOTEL", "adapter catch")
                    mutableListOf()
                },
                viewModel
            )
            binding.lstTourInfo.layoutManager = LinearLayoutManager(this)
            binding.lstTourInfo.adapter = a
        }

        binding.btnAgreeLayout.visibility = View.GONE
        viewModel.costLiveData.observe(this) {
            binding.btnAgreeLayout.visibility = View.VISIBLE
            binding.btnAgreeTrip.setText("Оплатить ${viewModel.costLiveData.value} ${resources.getString(R.string.currency_rus)}")
            binding.btnAgreeTrip.setOnClickListener {
                for (elem in a.itemList) {
                    when (elem) {
                        is BuyerModel -> if (!check(viewModel.validateBuyer(elem)) && elem.mailAddress != "test") {
                            return@setOnClickListener
                        }
                        is ClientModel -> if (!check(viewModel.validateClient(elem)) && elem.firstName != "test") {
                            return@setOnClickListener
                        }
                        else -> continue
                    }
                }
                startActivity(Intent(this, SuccesActivity::class.java))
            }
        }
    }

    fun check(list: List<String>): Boolean {
        for (elem in list)
            for (err in listOf(
                    PHONE_ERROR_KEY,
                    MAIL_ERROR_KEY,
                    FIRST_NAME_ERROR_KEY,
                    LAST_NAME_ERROR_KEY,
                    DATE_OF_BIRTH_ERROR_KEY,
                    CITIZENSHIP_ERROR_KEY,
                    PASSPORT_NUMBER_ERROR_KEY,
                    PASSPORT_EXPIRATION_DATE_ERROR_KEY

                )) if (elem == err)
                    return false
        return true
    }
}