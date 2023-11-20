package com.example.hotel.presentation.fragment

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.hotel.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Fragment for displaying an image with rounded corners.
 *
 * @param imageUrl The link to the image.
 */
class ImageFragment(private val imageUrl: String = "") : Fragment() {
    private lateinit var view: View

    /**
     * Creates and returns the fragment view.
     *
     * @param inflater The inflater to inflate the layout.
     * @param container The container into which the fragment is added.
     * @param savedInstanceState The saved state of the fragment, if any.
     * @return Returns the created view of the fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.layout_image_fragment, container, false)
        val imageView: ImageView = view.findViewById(R.id.imageViewFragment)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val roundedBitmap = getRoundedCornerBitmap(imageUrl)
                withContext(Dispatchers.Main) {
                    imageView.setImageBitmap(roundedBitmap)
                }
            } catch (e: Exception) {
                Log.e("HOTEL", e.message.toString())
                withContext(Dispatchers.Main){
                    imageView.setImageResource(R.mipmap.image_not_supported)
                }
            }
        }

        return view
    }

    /**
     * Gets a rounded corner bitmap of the image.
     *
     * @param imageUrl The link to the image.
     * @return Returns a rounded corner bitmap of the image.
     */
    private suspend fun getRoundedCornerBitmap(imageUrl: String): Bitmap =
        withContext(Dispatchers.IO) {
            try {
                Glide.with(requireContext())
                    .asBitmap()
                    .load(imageUrl)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(resources.getDimensionPixelSize(R.dimen.corner_radius))))
                    .submit()
                    .get()
            } catch (e: Exception) {
                throw e
            }
        }
}
