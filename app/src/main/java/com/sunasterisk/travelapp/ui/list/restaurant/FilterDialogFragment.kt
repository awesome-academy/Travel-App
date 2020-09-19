package com.sunasterisk.travelapp.ui.list.restaurant

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.SeekBar
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.custom.MaterialPickDialogFragment
import kotlinx.android.synthetic.main.dialog_filtter_restaurant.*
import kotlinx.android.synthetic.main.dialog_filtter_restaurant.view.*

class FilterDialogFragment :
    MaterialPickDialogFragment(),
    View.OnClickListener,
    RadioGroup.OnCheckedChangeListener,
    SeekBar.OnSeekBarChangeListener {

    private val priceDefault = 500
    private val ratingDefault = 3
    private var sortByPrice = true
    private var maxPrice = 500
    private var minRating = 3
    private lateinit var listener: OnDataChange
    private var viewFilter: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewFilter = inflater.inflate(R.layout.dialog_filtter_restaurant, container, false)
        getArgument()
        initView()
        return viewFilter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDataChange) listener = context
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        sortByPrice = checkedId == R.id.radioButtonPrice
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.textViewApply -> {
                maxPrice = seekBarPrice.progress
                minRating = seekBarRating.progress
                listener.sendData(sortByPrice, maxPrice, minRating)
                dismiss()
            }
            R.id.textViewReset -> {
                sortByPrice = true
                maxPrice = priceDefault
                minRating = ratingDefault
                setContent()
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (seekBar?.id == R.id.seekBarPrice) {
            textViewCurrentPrice.text =
                resources.getString(R.string.title_current_price, progress)
        } else {
            textViewCurrentRating.text =
                resources.getString(R.string.title_current_rating, progress)
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

    private fun getArgument() {
        arguments?.apply {
            if (containsKey(ARGUMENT_SORT_BY)) {
                sortByPrice = getBoolean(ARGUMENT_SORT_BY)
            }
            if (containsKey(ARGUMENT_MIN_RATING)) {
                minRating = getInt(ARGUMENT_MIN_RATING)
            }
            if (containsKey(ARGUMENT_MAX_PRICE)) {
                maxPrice = getInt(ARGUMENT_MAX_PRICE)
            }
        }
    }

    private fun initView() {
        dialog?.setCanceledOnTouchOutside(false)
        viewFilter?.let {
            it.radioGroupSort.setOnCheckedChangeListener(this)
            it.textViewApply.setOnClickListener(this)
            it.textViewReset.setOnClickListener(this)
            it.seekBarPrice.setOnSeekBarChangeListener(this)
            it.seekBarRating.setOnSeekBarChangeListener(this)
        }
    }

    private fun setContent() {
        if (sortByPrice) {
            radioButtonPrice.isChecked = true
            radioButtonRatting.isChecked = false
        } else {
            radioButtonPrice.isChecked = false
            radioButtonRatting.isChecked = true
        }
        seekBarPrice.progress = maxPrice
        seekBarRating.progress = minRating
    }

    companion object {
        private const val ARGUMENT_SORT_BY = "ARGUMENT_SORT_BY"
        private const val ARGUMENT_MAX_PRICE = "ARGUMENT_MAX_PRICE"
        private const val ARGUMENT_MIN_RATING = "ARGUMENT_MIN_RATING"

        fun newInstance(sortByPrice: Boolean, maxPrice: Int, minRating: Int): FilterDialogFragment {
            val fragment = FilterDialogFragment()
            val bundle = Bundle()
            bundle.putBoolean(ARGUMENT_SORT_BY, sortByPrice)
            bundle.putInt(ARGUMENT_MAX_PRICE, maxPrice)
            bundle.putInt(ARGUMENT_MIN_RATING, minRating)
            fragment.arguments = bundle
            return fragment
        }
    }

    interface OnDataChange {
        fun sendData(sortByPrice: Boolean, maxPrice: Int, minRating: Int)
    }
}
