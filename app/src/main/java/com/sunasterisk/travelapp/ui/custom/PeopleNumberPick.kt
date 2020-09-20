package com.sunasterisk.travelapp.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.DestinationFilter
import kotlinx.android.synthetic.main.layout_traveler_pick.view.*

class PeopleNumberPick @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var picker: TextView? = null
    private var numberChildren: Int = DestinationFilter.adultCount
    private var numberAdult: Int = DestinationFilter.childrenCount
    private var dialogFragment: DialogFragment? = null
    private var max: Int = resources.getInteger(R.integer.integer_10)
    private var min: Int = resources.getInteger(R.integer.integer_0)

    init {
        View.inflate(context, R.layout.layout_traveler_pick, this)
        textViewPickAdult.text = numberAdult.toString()
        textViewPickChildren.text = numberChildren.toString()
        handleEvents()
    }

    private fun handleEvents() {
        imageViewAdultMinus.setOnClickListener(this)
        imageViewAdultPlus.setOnClickListener(this)
        imageViewChildrenMinus.setOnClickListener(this)
        imageViewChildrenPlus.setOnClickListener(this)
        materialButtonCancel.setOnClickListener(this)
        materialButtonApply.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.imageViewAdultMinus -> decreaseAdult()
            R.id.imageViewAdultPlus -> increaseAdult()
            R.id.imageViewChildrenMinus -> decreaseChildren()
            R.id.imageViewChildrenPlus -> increaseChildren()
            R.id.materialButtonCancel -> cancelDialog()
            R.id.materialButtonApply -> submitResult()
        }
    }

    private fun submitResult() {
        DestinationFilter.adultCount = numberAdult
        DestinationFilter.childrenCount = numberChildren
        picker?.text = context.resources.getQuantityString(
            R.plurals.title_filter_travelers,
            context.resources.getInteger(R.integer.integer_1),
            DestinationFilter.adultCount, DestinationFilter.childrenCount
        )
        dialogFragment?.dismiss()
    }

    private fun cancelDialog() {
        dialogFragment?.dismiss()
    }

    private fun increaseChildren() {
        if (numberChildren !in min..max) return
        numberChildren++
        if (numberChildren > max) numberChildren = max
        if (numberChildren < min) numberChildren = min
        textViewPickChildren.text = numberChildren.toString()
    }

    private fun decreaseChildren() {
        if (numberChildren !in min..max) return
        numberChildren--
        if (numberChildren > max) numberChildren = max
        if (numberChildren < min) numberChildren = min
        textViewPickChildren.text = numberChildren.toString()
    }

    private fun increaseAdult() {
        if (numberAdult !in min..max) return
        numberAdult++
        if (numberAdult > max) numberAdult = max
        if (numberAdult < min) numberAdult = min
        textViewPickAdult.text = numberAdult.toString()
    }

    private fun decreaseAdult() {
        if (numberAdult !in min..max) return
        numberAdult--
        if (numberAdult > max) numberAdult = max
        if (numberAdult < min) numberAdult = min
        textViewPickAdult.text = numberAdult.toString()
    }

    fun setPicker(picker: TextView) {
        this.picker = picker
    }

    fun setDialogFragment(dialogFragment: DialogFragment) {
        this.dialogFragment = dialogFragment
    }
}
