package com.example.hotel.presentation.hotel

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hotel.R
import com.example.hotel.databinding.ActivityHotelMainBinding
import com.example.hotel.special.adapter.image.ImageFragmentAdapter
import com.example.hotel.presentation.room.RoomChangeActivity
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import org.koin.androidx.viewmodel.ext.android.viewModel


class HotelMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHotelMainBinding
    private val viewModel by viewModel<HotelMainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.hotelLiveData.observe(this) {
            binding.carouselView.adapter = ImageFragmentAdapter(
                context = this,
                imageUrls = it.image_urls,
                scrollbarLayout = binding.scrollbar,
                viewPager = binding.carouselView
            )
            binding.rating.text = "${it.rating} ${it.rating_name}"
            binding.hotelName.text = "${it.name}"
            binding.hotelAddress.text = "${it.adress}"
            binding.hotelCost.text = "от ${it.minimal_price} ${resources.getString(R.string.currency_rus)}"
            binding.priceForIt.text = "${it.price_for_it}"
            binding.aboutHotelBody.text = "${it.about_the_hotel.description}"


            val flexboxLayout = FlexboxLayout(this)
            flexboxLayout.flexDirection = FlexDirection.ROW
            flexboxLayout.flexWrap = FlexWrap.WRAP

            for (item in it.about_the_hotel.peculiarities) {
                val textView = TextView(this)
                textView.text = item

                val layoutParams = FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT
                )

                layoutParams.setMargins(
                    0,
                    0,
                    resources.getDimensionPixelSize(R.dimen.margin8),
                    resources.getDimensionPixelSize(R.dimen.margin8)
                )

                textView.layoutParams = layoutParams
                textView.setPadding(
                    resources.getDimensionPixelSize(R.dimen.specific10_padding_h),
                    resources.getDimensionPixelSize(R.dimen.specific5_padding_h),
                    resources.getDimensionPixelSize(R.dimen.specific10_padding_h),
                    resources.getDimensionPixelSize(R.dimen.specific5_padding_h)
                )
                textView.setTextColor(resources.getColor(R.color.gray))
                textView.textSize = 16f
                textView.setBackgroundResource(R.drawable.radius_bg_five)
                textView.background = resources.getDrawable(R.color.background_color)
                flexboxLayout.addView(textView)
            }

            binding.aboutHotelTable.addView(flexboxLayout)

            binding.btnChangeHotel.setOnClickListener { _ ->
                val intent = Intent(this, RoomChangeActivity::class.java)
                intent.putExtra("HOTEL_NAME", it.name)
                startActivity(intent)
            }
        }



    }


}