package com.sunasterisk.travelapp.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Scroller
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.viewpager.widget.ViewPager
import com.sunasterisk.travelapp.R

class SwipeControllableViewPager constructor(
    context: Context,
    attrs: AttributeSet
) : ViewPager(context, attrs) {
    var swipeEnable = false

    init {
        val scroller = ViewPager::class.java.getDeclaredField(resources.getString(R.string.title_view_pager_field))
        try {
            scroller.isAccessible = true
            scroller.set(this, FastOutSlowInScroller(context, FastOutSlowInInterpolator()))
        } finally {
            scroller.isAccessible = false
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?) =
        if (this.swipeEnable) {
            super.onInterceptTouchEvent(ev)
        } else false

}

class FastOutSlowInScroller(
    context: Context,
    interpolator: android.view.animation.Interpolator
) : Scroller(context, interpolator)
