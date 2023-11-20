package com.example.hotel.special.adapter.reservation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.BuyerModel
import com.example.domain.model.ClientAddModel
import com.example.domain.model.ClientModel
import com.example.domain.model.ReservationCostModel
import com.example.domain.model.ReservationHotelModel
import com.example.domain.model.ReservationTourModel
import com.example.hotel.R
import com.example.hotel.special.adapter.reservation.holder.BuyerInfoViewHolder
import com.example.hotel.special.adapter.reservation.holder.ClientAddViewHolder
import com.example.hotel.special.adapter.reservation.holder.ClientInfoViewHolder
import com.example.hotel.special.adapter.reservation.holder.CostInfoViewHolder
import com.example.hotel.special.adapter.reservation.holder.HotelInfoViewHolder
import com.example.hotel.special.adapter.reservation.holder.TourInfoViewHolder
import com.example.hotel.special.interfaces.OnEditedCallback
import java.lang.Exception

/**
 * Adapter for displaying various information in a RecyclerView related to room reservations.
 *
 * @param context The context of the calling activity or fragment.
 * @param itemList The list of items to be displayed in the adapter.
 * @param onEditedCallback Callback interface for handling user interactions and edits.
 */
class ReservationMultiAdapter(
    private val context: Context,
    val itemList: MutableList<Any>,
    private val onEditedCallback: OnEditedCallback
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val clientLabel = listOf(
        "Первый клиент",
        "Второй клиент",
        "Третий клиент",
        "Четвёртый клиент",
        "Пятый клиент",
        "Шестой клиент",
        "Седьмой клиент",
        "Восьмой клиент",
        "Девятый клиент",
        "Десятый клиент"
    )

    /**
     * Creates a new ViewHolder instance based on the view type.
     *
     * @param parent The parent view group.
     * @param viewType The type of the view.
     * @return A new ViewHolder instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HOTEL_INFO -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_reservation_where_fragment, parent, false)
                HotelInfoViewHolder(view)
            }
            TOUR_INFO -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_reservation_about_trip_fragment, parent, false)
                TourInfoViewHolder(view)
            }
            BUYER_INFO -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_reservation_about_buyer_fragment, parent, false)
                BuyerInfoViewHolder(view, onEditedCallback)
            }
            CLIENT_INFO -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_reservation_tourist_fragment, parent, false)
                ClientInfoViewHolder(context, view, onEditedCallback, )
            }
            ADD_CLIENT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_reservation_add_turist_fragment, parent, false)
                ClientAddViewHolder(view)
            }
            COST_INFO -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_reservation_about_trip_cost_fragment, parent, false)
                CostInfoViewHolder(view, onEditedCallback)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    /**
     * Binds data to the ViewHolder based on its type.
     *
     * @param holder The ViewHolder instance to bind data to.
     * @param position The position of the item in the data set.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HotelInfoViewHolder -> holder.bind(itemList[position] as ReservationHotelModel)
            is TourInfoViewHolder -> holder.bind(itemList[position] as ReservationTourModel)
            is BuyerInfoViewHolder -> holder.bind(itemList[position] as BuyerModel)
            is ClientInfoViewHolder -> holder.bind(itemList[position] as ClientModel,
                if (position - 3 < clientLabel.size) clientLabel[position - 3] else "Клиент ${position - 2}"
            )
            is ClientAddViewHolder -> holder.bind(itemList[position] as ClientAddModel) {
                itemList.add(position, ClientModel())
                notifyDataSetChanged()
            }
            is CostInfoViewHolder -> holder.bind(context = context, itemList[position] as ReservationCostModel)
        }
    }

    /**
     * Returns the total number of items in the data set.
     *
     * @return The total number of items.
     */
    override fun getItemCount(): Int = itemList.size

    /**
     * Returns the view type of the item at the specified position.
     *
     * @param position The position of the item.
     * @return The view type of the item.
     */
    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is ReservationHotelModel -> HOTEL_INFO
            is ReservationTourModel -> TOUR_INFO
            is BuyerModel -> BUYER_INFO
            is ClientModel -> CLIENT_INFO
            is ClientAddModel -> ADD_CLIENT
            is ReservationCostModel -> COST_INFO
            else -> throw Exception("Adapter haven't such model: ${itemList[position]}")
        }
    }

    private companion object {
        const val HOTEL_INFO = 1
        const val TOUR_INFO = 2
        const val BUYER_INFO = 3
        const val CLIENT_INFO = 4
        const val ADD_CLIENT = 5
        const val COST_INFO = 6
    }
}
