package com.example.hotel.presentation.room

import com.example.hotel.special.adapter.room.RoomFragmentAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotel.databinding.ActivityRoomChangeBinding
import com.example.hotel.presentation.reservation.RoomReservationActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomChangeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomChangeBinding
    private val viewModel by viewModel<RoomChangeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hotelName.text = intent.getStringExtra("HOTEL_NAME")

        binding.btnBack.setOnClickListener { finish() }

        viewModel.roomsLiveData.observe(this) {
            val adapter = RoomFragmentAdapter(this, it) {
                startActivity(Intent(this@RoomChangeActivity,
                    RoomReservationActivity::class.java))
            }

            binding.recycler.layoutManager = LinearLayoutManager(this)
            binding.recycler.adapter = adapter

        }

    }
}