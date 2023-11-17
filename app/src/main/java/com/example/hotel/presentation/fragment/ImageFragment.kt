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


class ImageFragment(private val imageUrl: String) : Fragment() {
    private lateinit var view: View
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

//    private fun getRoundedCornerBitmap(bitmap: Bitmap, pixels: Float): Bitmap {
//        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(output)
//        val paint = Paint()
//        val rect = Rect(0, 0, bitmap.width, bitmap.height)
//        val rectF = RectF(rect)
//
//        paint.isAntiAlias = true
//        canvas.drawRoundRect(rectF, pixels, pixels, paint)
//
//        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
//        canvas.drawBitmap(bitmap, rect, rect, paint)
//
//        return output
//    }

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