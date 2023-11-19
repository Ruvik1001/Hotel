package com.example.hotel.special.adapter.explist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.EditText
import android.widget.ExpandableListView
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TextView
import com.example.domain.model.ClientModel
import com.example.domain.model.ExpModel
import com.example.hotel.R
import com.example.hotel.special.interfaces.OnEdited

class ExpListAdapter(
    private val context: Context,
    private val expandableListView: ExpandableListView,
    val groups: List<ExpModel>,
    private val onEdited: OnEdited
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int = groups.size

    override fun getChildrenCount(groupPosition: Int): Int = 1

    override fun getGroup(groupPosition: Int): Any = groups[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Any =
        groups[groupPosition].child

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long =
        (groupPosition * 1000 + childPosition).toLong()

    override fun hasStableIds(): Boolean = true

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_reservation_group_view, parent, false)
        val groupTitle = view.findViewById<TextView>(R.id.tourist_title)
        val indicator = view.findViewById<ImageButton>(R.id.indicator)
        indicator.foreground = context.resources.getDrawable(
            if (isExpanded) R.drawable.ind_open
            else R.drawable.ind_close
        )
        indicator.setOnClickListener {
            val position = expandableListView.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupPosition))
            expandableListView.performItemClick(view, position, expandableListView.getItemIdAtPosition(position))
        }
        groupTitle.text = (getGroup(groupPosition) as ExpModel).title
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_reservation_turist_info_fragment, parent, false)
        val clientModel = (getChild(groupPosition, childPosition) as ClientModel)

        val name = view.findViewById<EditText>(R.id.name)
        name.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                clientModel.firstName = name.text.toString()
                onEdited.updateName(clientModel, view.findViewById(R.id.filed_name))
            }
        }
        val lastName = view.findViewById<EditText>(R.id.last_name)
        lastName.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                clientModel.lastName = lastName.text.toString()
                onEdited.updateLastName(clientModel, view.findViewById<TableLayout>(R.id.filed_last_name))
            }
        }
        val dateOfBirth = view.findViewById<EditText>(R.id.date_of_birth)
        dateOfBirth.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                clientModel.dateOfBirth = dateOfBirth.text.toString()
                onEdited.updateDateOfBirth(clientModel, view.findViewById<TableLayout>(R.id.filed_date_of_birth))
            }
        }
        val citizenship = view.findViewById<EditText>(R.id.citizenship)
        citizenship.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                clientModel.citizenship = citizenship.text.toString()
                onEdited.updateCitizenship(clientModel, view.findViewById<TableLayout>(R.id.filed_citizenship))
            }
        }
        val passport = view.findViewById<EditText>(R.id.passport_ext)
        passport.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                clientModel.passportNumber = passport.text.toString()
                onEdited.updatePassportNumber(clientModel, view.findViewById<TableLayout>(R.id.filed_passport_ext))
            }
        }
        val passportDate = view.findViewById<EditText>(R.id.passport_date_to_actual)
        passportDate.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                clientModel.passportExpirationDate = passportDate.text.toString()
                onEdited.updatePassportExpirationDate(clientModel, view.findViewById<TableLayout>(R.id.filed_passport_date_to_actual))
            }
        }

        name.setText(clientModel.firstName)
        lastName.setText(clientModel.lastName)
        dateOfBirth.setText(clientModel.dateOfBirth)
        citizenship.setText(clientModel.citizenship)
        passport.setText(clientModel.passportNumber)
        passportDate.setText(clientModel.passportExpirationDate)

        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
}