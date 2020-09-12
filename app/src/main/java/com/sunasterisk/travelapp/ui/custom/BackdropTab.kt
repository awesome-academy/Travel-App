package com.sunasterisk.travelapp.ui.custom

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.utils.*
import kotlinx.android.synthetic.main.layout_list_hotel_tab.view.*

class BackdropTab(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var backdropShown = false
    private var animatorSet = AnimatorSet()
    private var sheet: View? = null
    private var translateY = 0
    private var flowGroupRefs = mutableListOf<@IdRes Int>()

    init {
        View.inflate(context, R.layout.layout_list_hotel_tab, this)
        handleEvents()
    }

    private fun handleEvents() {
        materialButtonPersonPick.setOnClickListener(this)
        materialButtonDateRangePick.setOnClickListener(this)
        materialButtonLocationPick.setOnClickListener(this)
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun clickedItem(view: View) {

        backdropShown = !backdropShown

        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()

        val animator = ObjectAnimator.ofFloat(
            sheet, resources.getString(R.string.title_transition_type_y),
            (if (backdropShown) translateY else 0).toFloat()
        )
        animator.duration = (resources.getInteger(R.integer.integer_500)).toLong()
        animator.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.play(animator)
        animator.start()
        flowGroupRefs = flowGroupTab.referencedIds.toMutableList()

        val check = flowGroupRefs.all { id -> (findViewById<MaterialButton>(id).isVisible) }
        flowGroupRefs.filter { it != view.id }.apply {
            if (check) {
                forEach {
                    findViewById<MaterialButton>(it).setTextColor(Color.GRAY)
                    findViewById<MaterialButton>(it).gone()
                }
            } else {
                forEach {
                    findViewById<MaterialButton>(it).show()
                }
            }
            (view as MaterialButton).setTextColor(Color.WHITE)
            sheet?.clearAnimation()
        }
    }

    fun updateTabsName(tabName: TabName) {
        materialButtonPersonPick.text = tabName.personTabName
        materialButtonDateRangePick.text = tabName.dateRangeTabName
        materialButtonLocationPick.text = tabName.locationTabName
    }

    fun setView(view: View) {
        sheet = view
    }

    fun setTranslateY(translate: Int) {
        translateY = translate
    }

    override fun onClick(view: View) = clickedItem(view)

}

data class TabName(
    val personTabName: String,
    val dateRangeTabName: String,
    val locationTabName: String
) {
    companion object {
        const val PERSON_TAB_KEY: String = "person_tab_key"
        const val DATE_RANGE_TAB_KEY: String = "date_range_tab_key"
        const val LOCATION_TAB_KEY: String = "location_tab_key"
    }
}
