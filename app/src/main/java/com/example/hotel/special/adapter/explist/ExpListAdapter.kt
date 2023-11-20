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
import com.example.hotel.special.interfaces.OnEditedCallback

/**
 * Adapter for displaying an ExpandableListView with information about clients in a reservation.
 *
 * @param context The context.
 * @param expandableListView The ExpandableListView to display.
 * @param groups The list of ExpModel representing groups in the ExpandableListView.
 * @param onEditedCallback The callback for handling edited information.
 */
class ExpListAdapter(
    private val context: Context,
    private val expandableListView: ExpandableListView,
    val groups: List<ExpModel>,
    private val onEditedCallback: OnEditedCallback
) : BaseExpandableListAdapter() {

    /**
     * Gets the number of groups in the adapter.
     *
     * @return The number of groups.
     */
    override fun getGroupCount(): Int = groups.size

    /**
     * Gets the number of children in a group.
     *
     * @param groupPosition The position of the group.
     * @return The number of children in the group.
     */
    override fun getChildrenCount(groupPosition: Int): Int = 1

    /**
     * Gets the group at a specified position.
     *
     * @param groupPosition The position of the group.
     * @return The group at the specified position.
     */
    override fun getGroup(groupPosition: Int): Any = groups[groupPosition]

    /**
     * Gets the child at a specified position.
     *
     * @param groupPosition The position of the group.
     * @param childPosition The position of the child within the group.
     * @return The child at the specified position.
     */
    override fun getChild(groupPosition: Int, childPosition: Int): Any =
        groups[groupPosition].child

    /**
     * Gets the ID for a group.
     *
     * @param groupPosition The position of the group.
     * @return The ID for the group.
     */
    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    /**
     * Gets the ID for a child within a group.
     *
     * @param groupPosition The position of the group.
     * @param childPosition The position of the child within the group.
     * @return The ID for the child.
     */
    override fun getChildId(groupPosition: Int, childPosition: Int): Long =
        (groupPosition * 1000 + childPosition).toLong()

    /**
     * Indicates whether the IDs for groups and children are stable across changes.
     *
     * @return `true` if the IDs are stable, otherwise `false`.
     */
    override fun hasStableIds(): Boolean = true

    /**
     * Gets the view for a group.
     *
     * @param groupPosition The position of the group.
     * @param isExpanded Indicates whether the group is expanded.
     * @param convertView The recycled view to use for the group.
     * @param parent The parent ViewGroup.
     * @return The view for the group.
     */
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

    /**
     * Gets the view for a child within a group.
     *
     * @param groupPosition The position of the group.
     * @param childPosition The position of the child within the group.
     * @param isLastChild Indicates whether the child is the last in the group.
     * @param convertView The recycled view to use for the child.
     * @param parent The parent ViewGroup.
     * @return The view for the child.
     */
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
                onEditedCallback.updateName(clientModel, view.findViewById(R.id.filed_name))
            }
        }
        val lastName = view.findViewById<EditText>(R.id.last_name)
        lastName.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                clientModel.lastName = lastName.text.toString()
                onEditedCallback.updateLastName(clientModel, view.findViewById<TableLayout>(R.id.filed_last_name))
            }
        }
        val dateOfBirth = view.findViewById<EditText>(R.id.date_of_birth)
        dateOfBirth.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                clientModel.dateOfBirth = dateOfBirth.text.toString()
                onEditedCallback.updateDateOfBirth(clientModel, view.findViewById<TableLayout>(R.id.filed_date_of_birth))
            }
        }
        val citizenship = view.findViewById<EditText>(R.id.citizenship)
        citizenship.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                clientModel.citizenship = citizenship.text.toString()
                onEditedCallback.updateCitizenship(clientModel, view.findViewById<TableLayout>(R.id.filed_citizenship))
            }
        }
        val passport = view.findViewById<EditText>(R.id.passport_ext)
        passport.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                clientModel.passportNumber = passport.text.toString()
                onEditedCallback.updatePassportNumber(clientModel, view.findViewById<TableLayout>(R.id.filed_passport_ext))
            }
        }
        val passportDate = view.findViewById<EditText>(R.id.passport_date_to_actual)
        passportDate.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                clientModel.passportExpirationDate = passportDate.text.toString()
                onEditedCallback.updatePassportExpirationDate(clientModel, view.findViewById<TableLayout>(R.id.filed_passport_date_to_actual))
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

    /**
     * Indicates whether a child is selectable.
     *
     * @param groupPosition The position of the group.
     * @param childPosition The position of the child within the group.
     * @return `true` if the child is selectable, otherwise `false`.
     */
    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
}
