package com.example.hotel.presentation.reservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotel.R
import com.example.hotel.databinding.ActivityRoomChangeBinding
import com.example.hotel.databinding.ActivityRoomReservatiomBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomReservationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomReservatiomBinding
    private val viewModel by viewModel<RoomReservationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomReservatiomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        viewModel.roomLiveData.observe(this) {

        }
    }
}