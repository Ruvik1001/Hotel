package com.example.hotel.special.adapter.reservation.holder

import android.content.Context
import android.view.View
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.ClientModel
import com.example.domain.model.ExpModel
import com.example.hotel.R
import com.example.hotel.special.adapter.explist.ExpListAdapter
import com.example.hotel.special.interfaces.OnEdited

class ClientInfoViewHolder(
    private val context: Context,
    private val itemView: View,
    private val onEdited: OnEdited
) : RecyclerView.ViewHolder(itemView) {

    fun bind(clientModel: ClientModel, title: String) {
        val expList = itemView.findViewById<ExpandableListView>(R.id.exp_list)

        val adapter = ExpListAdapter(
            context = context,
            expList,
            groups = listOf(ExpModel(title, clientModel)),
            onEdited
        )
        expList.setOnGroupClickListener { _, _, groupPosition, _ ->
            if (expList.isGroupExpanded(groupPosition)) {
                expList.collapseGroup(groupPosition)
            } else {
                expList.expandGroup(groupPosition)
            }
            updateExpandableListViewHeight(expList)
            true
        }
        expList.setAdapter(adapter)

        updateExpandableListViewHeight(expList)
    }

    private fun updateExpandableListViewHeight(expandableListView: ExpandableListView) {
        val params = expandableListView.layoutParams
        params.height = getTotalHeightOfListView(expandableListView)
        expandableListView.layoutParams = params
        expandableListView.requestLayout()
    }

    private fun getTotalHeightOfListView(listView: ExpandableListView): Int {
        val adapter = listView.expandableListAdapter
        var totalHeight = 0
        val measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)

        for (i in 0 until adapter.groupCount) {
            val groupItem = adapter.getGroupView(i, false, null, listView)
            groupItem.measure(measureSpec, measureSpec)
            totalHeight += groupItem.measuredHeight

            if (listView.isGroupExpanded(i)) {
                for (j in 0 until adapter.getChildrenCount(i)) {
                    val listItem = adapter.getChildView(i, j, false, null, listView)
                    listItem.measure(measureSpec, measureSpec)
                    totalHeight += listItem.measuredHeight
                }
            }
        }

        return totalHeight + (listView.dividerHeight * (adapter.groupCount - 1))
    }
}
