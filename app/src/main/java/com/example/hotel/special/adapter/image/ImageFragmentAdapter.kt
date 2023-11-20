package com.example.hotel.special.adapter.image

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

/**
 * Adapter for displaying images in a ViewPager2 with an optional scrollbar.
 *
 * @param context The context.
 * @param imageUrls The list of image URLs to display.
 * @param scrollbarLayout The layout for the optional scrollbar.
 * @param viewPager The ViewPager2 associated with the adapter.
 */
class ImageFragmentAdapter(
    private val context: Context,
    private var imageUrls: List<String>? = listOf(),
    private val scrollbarLayout: LinearLayout? = null,
    private val viewPager: ViewPager2? = null
) : FragmentStateAdapter(context as FragmentActivity) {

    private var scrollbar: Scrollbar? = null

    /**
     * Helper class for managing the scrollbar associated with the ViewPager2.
     *
     * @param context The context.
     * @param scrollbarLayout The layout for the scrollbar.
     * @param countItem The number of items to display in the scrollbar.
     */
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

        /**
         * Updates the scrollbar based on the selected position in the ViewPager2.
         *
         * @param selectedPosition The currently selected position in the ViewPager2.
         */
        fun updateScrollbar(selectedPosition: Int) {
            for (i in 0 until scrollbarLayout.childCount) {
                val item = scrollbarLayout.getChildAt(i) as ImageView
                item.isSelected = i == selectedPosition
                item.setColorFilter(calculateNewBaseColor(scrollbarLayout.childCount, i, selectedPosition))
            }
        }

        /**
         * Calculates the new base color for a scrollbar item.
         *
         * @param length The total number of items in the scrollbar.
         * @param position The position of the current item.
         * @param activePosition The position of the active (selected) item.
         * @return The new base color for the item.
         */
        fun calculateNewBaseColor(length: Int, position: Int, activePosition: Int): Int {
            val baseColor = Color.BLACK
            var alpha = Color.alpha(baseColor)

            val brightnessFactor = 1f + 33 * (kotlin.math.abs(position - activePosition).toFloat() / length.toFloat())
            alpha = (alpha.toFloat() / brightnessFactor).toInt()

            return Color.argb(alpha, Color.red(Color.BLACK), Color.green(Color.BLACK), Color.blue(
                Color.BLACK))
        }
    }

    /**
     * Gets the number of items in the adapter.
     *
     * @return The number of items.
     */
    override fun getItemCount(): Int {
        return imageUrls?.size ?: 0
    }

    /**
     * Creates a new fragment for the specified position.
     *
     * @param position The position of the item.
     * @return The created fragment.
     */
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
