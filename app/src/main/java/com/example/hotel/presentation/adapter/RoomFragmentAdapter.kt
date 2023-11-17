import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TableLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.domain.model.RoomModel
import com.example.domain.model.RoomsListModel
import com.example.hotel.R
import com.example.hotel.presentation.adapter.ImageFragmentAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout

class RoomFragmentAdapter(
    private val context: Context,
    private val roomsListModel: RoomsListModel?,
    private val onClick: ()->Unit
) : RecyclerView.Adapter<RoomFragmentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: FrameLayout = view.findViewById(R.id.fragmentContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_room_fragment, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val roomModel = roomsListModel?.rooms?.get(position)
            ?: RoomModel(0, "", 0, "", listOf(""), listOf(""))

        holder.container.findViewById<ViewPager2>(R.id.carouselView).adapter = ImageFragmentAdapter(
            context = context,
            imageUrls = roomModel.image_urls,
            scrollbarLayout = holder.container.findViewById(R.id.scrollbar),
            holder.container.findViewById(R.id.carouselView)
        )

        holder.container.findViewById<TextView>(R.id.room_name).text = roomModel.name

        val flexboxLayout = FlexboxLayout(context)
        flexboxLayout.flexDirection = FlexDirection.ROW
        flexboxLayout.flexWrap = FlexWrap.WRAP

        for (item in roomModel.peculiarities) {
            val textView = TextView(context)
            textView.text = item

            val layoutParams = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT
            )

            layoutParams.setMargins(
                0,
                0,
                context.resources.getDimensionPixelSize(R.dimen.margin8),
                context.resources.getDimensionPixelSize(R.dimen.margin8)
            )

            textView.layoutParams = layoutParams
            textView.setPadding(
                context.resources.getDimensionPixelSize(R.dimen.specific10_padding_h),
                context.resources.getDimensionPixelSize(R.dimen.specific5_padding_h),
                context.resources.getDimensionPixelSize(R.dimen.specific10_padding_h),
                context.resources.getDimensionPixelSize(R.dimen.specific5_padding_h)
            )
            textView.setTextColor(context.resources.getColor(R.color.gray))
            textView.textSize = 16f
            textView.setBackgroundResource(R.drawable.radius_bg_five)
            textView.background = context.resources.getDrawable(R.color.background_color)
            flexboxLayout.addView(textView)
        }

        holder.container.findViewById<TableLayout>(R.id.about_room_table).addView(flexboxLayout)

        holder.container.findViewById<TextView>(R.id.room_cost).text = "${roomModel.price} ${context.resources.getString(R.string.currency_rus)}"

        holder.container.findViewById<TextView>(R.id.price_for_it).text = "${roomModel.price_per}"

        holder.container.findViewById<TextView>(R.id.btn_change_room).setOnClickListener {
            onClick()
        }
    }

    override fun getItemCount(): Int {
        return roomsListModel?.rooms?.size ?: 0
    }
}

