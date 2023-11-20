package com.example.hotel.presentation.succes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.hotel.R
import com.example.data.repository.ClientRepository
import com.example.hotel.presentation.hotel.HotelMainActivity

/**
 * Activity displayed upon successful reservation.
 */
class SuccessActivity : AppCompatActivity() {
    val clientRepository = ClientRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_succes)
        findViewById<ImageView>(R.id.btn_back).setOnClickListener { finish() }
        findViewById<TextView>(R.id.order)
            .setText("${resources.getString(R.string.succes_body_half1)} " +
                    "${clientRepository.generateRandomTicket()} ${resources.getString(R.string.success_body_half2)}")
        findViewById<Button>(R.id.btn_end).setOnClickListener {
            val intent = Intent(this, HotelMainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}