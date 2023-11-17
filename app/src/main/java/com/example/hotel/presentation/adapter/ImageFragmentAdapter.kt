package com.example.hotel.presentation.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.hotel.R
import com.example.hotel.presentation.fragment.ImageFragment

class ImageFragmentAdapter(
    private val context: Context,
    private var imageUrls: List<String>? = listOf(),
    private val scrollbarLayout: LinearLayout? = null,
    private val viewPager: ViewPager2? = null
) : FragmentStateAdapter(context as FragmentActivity) {

    private var scrollbar: Scrollbar? = null

    private class Scrollbar(private val context: Context, private val scrollbarLayout: LinearLayout, private val countItem: Int) {
        init {
            for (i in 0 until countItem)
                addScrollbarItem()
        }

        private fun addScrollbarItem() {
            val item = ImageView(context)
            val layoutParams = LinearLayout.LayoutParams(
                context.resources.getDimensionPixelSize(R.dimen.scrollbar_item_size),
                context.resources.getDimensionPixelSize(R.dimen.scrollbar_item_size)
            )
            if (scrollbarLayout.childCount > 0) {
                layoutParams.leftMargin = context.resources.getDimensionPixelSize(R.dimen.scrollbar_item_margin)
            }

            item.layoutParams = layoutParams
            item.setImageResource(R.drawable.scrollbar_item_selector)
            scrollbarLayout.addView(item)
        }

        fun updateScrollbar(selectedPosition: Int) {
            for (i in 0 until scrollbarLayout.childCount) {
                val item = scrollbarLayout.getChildAt(i) as ImageView
                item.isSelected = i == selectedPosition
                item.setColorFilter(calculateNewBaseColor(scrollbarLayout.childCount, i, selectedPosition))
            }
        }

        fun calculateNewBaseColor(length: Int, position: Int, activePosition: Int): Int {
            val baseColor = Color.BLACK
            var alpha = Color.alpha(baseColor)

            val brightnessFactor = 1f + 33 * (kotlin.math.abs(position - activePosition).toFloat() / length.toFloat())
            alpha = (alpha.toFloat() / brightnessFactor).toInt()

            return Color.argb(alpha, Color.red(Color.BLACK), Color.green(Color.BLACK), Color.blue(
                Color.BLACK))
        }
    }

    override fun getItemCount(): Int {
        return imageUrls?.size ?: 0
    }

    override fun createFragment(position: Int): Fragment {
        return ImageFragment(imageUrls?.get(position) ?: "")
    }

    init {
        if (imageUrls == null)
            imageUrls = listOf("")
        if (scrollbarLayout != null) {
            scrollbar = Scrollbar(context, scrollbarLayout, imageUrls!!.size)
            viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    scrollbar!!.updateScrollbar(position)
                }
            })
        }
    }
}